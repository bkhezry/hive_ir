/*
 * Copyright (c) 2016. Behrouz Khezry
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.hive.fragment;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.bkhezry.extrawebview.ExtraWebViewCreator;
import com.github.bkhezry.extrawebview.data.DataModel;
import com.github.bkhezry.extrawebview.data.DataModelBuilder;
import com.github.bkhezry.extrawebview.data.IntentServiceResult;
import com.github.bkhezry.extrawebview.data.ThemePreference;
import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.cache.ConnectionBuddyCache;
import com.zplesac.connectionbuddy.interfaces.ConnectivityChangeListener;
import com.zplesac.connectionbuddy.models.ConnectivityEvent;
import com.zplesac.connectionbuddy.models.ConnectivityState;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import ir.hive.MainActivity;
import ir.hive.R;
import ir.hive.adapter.PostBlogAdapter;
import ir.hive.dao.Article;
import ir.hive.dao.DataBaseHandler;
import ir.hive.listener.EndlessRecyclerViewScrollListener;
import ir.hive.listener.RecyclerItemClickListener;
import ir.hive.pojo.PostBlogPOJO;
import ir.hive.services.APIServices;
import ir.hive.services.RetrofitUtility;
import ir.hive.utility.Converter;
import ir.hive.utility.GridSpacingItemDecoration;
import ir.hive.utility.TabEventInfo;
import ir.hive.utility.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.hive.MainActivity.SAVED_ARTICLE;
import static ir.hive.utility.Converter.PostBlogToArticle;


public class ArticleFragment extends Fragment implements ConnectivityChangeListener {
    private static final int PER_PAGE = 5;
    private static final String INT_FRAGMENT = "INT";
    private RecyclerView recyclerView;
    private PostBlogAdapter adapter;
    private List<PostBlogPOJO> postBlogPOJOList;
    private List<PostBlogPOJO> postBlogPOJOListAll = new ArrayList<>();
    private EndlessRecyclerViewScrollListener scrollListener;
    private SmoothProgressBar smoothProgressBar;
    private CircularProgressBar circularProgressBar;
    private DataModel dataModel;
    private View rootView;
    private LongSparseArray<PostBlogPOJO> postBlogPOJOLongSparseArray = new LongSparseArray<>();
    private DataBaseHandler dataBaseHandler;
    private int typeArticle;
    private Typeface typeface;
    private int pageGlobal = 0;
    private MaterialDialog errorConnectionDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_article,
                container, false);
        if (savedInstanceState != null) {
            ConnectionBuddyCache.clearLastNetworkState(getActivity());
        }
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.ic_wifi_white_48px);
        errorConnectionDialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.title)
                .titleGravity(GravityEnum.END)
                .content(R.string.content)
                .contentGravity(GravityEnum.END)
                .cancelable(true)
                .autoDismiss(false)
                .icon(drawable)
                .typeface(typeface, typeface)
                .positiveText(R.string.agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).build();
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IRANSansMobile.ttf");
        typeArticle = (getArguments().getInt(INT_FRAGMENT));
        dataBaseHandler = new DataBaseHandler(getActivity());
        EventBus.getDefault().register(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        smoothProgressBar = (SmoothProgressBar) rootView.findViewById(R.id.smooth_progressbar);
        circularProgressBar = (CircularProgressBar) rootView.findViewById(R.id.circle_progressbar);
        postBlogPOJOList = new ArrayList<>();
        adapter = new PostBlogAdapter(getActivity(), postBlogPOJOList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (typeArticle != MainActivity.SAVED_ARTICLE) {
                    if (!Utility.hasConnection(getActivity())) {
                        connectionDialog();
                    } else {
                        preparePostBlog(++pageGlobal);
                    }


                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                generateWebView(ThemePreference.THEME_NAMES.get(1), postBlogPOJOListAll.get(position));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, Converter.dpToPx(5, getResources()), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        if (typeArticle == SAVED_ARTICLE) {
            getPostBlogFromDB();
        } else {
            if (!Utility.hasConnection(getActivity())) {
                connectionDialog();
            }
        }
        return rootView;
    }

    private void connectionDialog() {
        errorConnectionDialog.show();
    }

    private void getPostBlogFromDB() {
        smoothProgressBar.setVisibility(View.INVISIBLE);
        circularProgressBar.setVisibility(View.INVISIBLE);
        List<Article> articles = dataBaseHandler.getArticles();
        List<PostBlogPOJO> postBlogPOJOs = Converter.ArticlesToPostBlog(articles);
        postBlogPOJOListAll = new ArrayList<>();
        addDataToView(postBlogPOJOs);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doBookmark(IntentServiceResult intentServiceResult) {
        if (intentServiceResult.isChecked()) {
            addBookmark(intentServiceResult.getId());
        } else {
            removeBookmark(intentServiceResult.getId());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doReload(TabEventInfo tabEventInfo) {
        recyclerView.smoothScrollToPosition(0);
    }

    private void removeBookmark(Long id) {
        dataBaseHandler.removeBookmark(id);

    }


    private void addBookmark(long id) {
        if (typeArticle != SAVED_ARTICLE) {
            dataBaseHandler.addBookmark(PostBlogToArticle(postBlogPOJOLongSparseArray.get(id)));
        }
    }

    private void preparePostBlog(int page) {
        smoothProgressBar.setVisibility(View.VISIBLE);
        APIServices apiServices = RetrofitUtility.getRetrofit().create(APIServices.class);
        Call<List<PostBlogPOJO>> call = apiServices.getPostPlogsService(PER_PAGE, page);
        call.enqueue(new Callback<List<PostBlogPOJO>>() {
            @Override
            public void onResponse(Call<List<PostBlogPOJO>> call, Response<List<PostBlogPOJO>> response) {
                smoothProgressBar.setVisibility(View.INVISIBLE);
                circularProgressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    List<PostBlogPOJO> postBlogPOJOs = response.body();
                    addDataToView(postBlogPOJOs);
                }
            }

            @Override
            public void onFailure(Call<List<PostBlogPOJO>> call, Throwable t) {
                smoothProgressBar.setVisibility(View.INVISIBLE);
                circularProgressBar.setVisibility(View.INVISIBLE);
                connectionDialog();
            }
        });

    }

    private void addDataToView(List<PostBlogPOJO> postBlogPOJOs) {
        postBlogPOJOListAll.addAll(postBlogPOJOs);
        postBlogPOJOList.clear();
        postBlogPOJOList.addAll(postBlogPOJOListAll);
        adapter.notifyDataSetChanged();
        scrollListener.resetState();
        for (PostBlogPOJO postBlogPOJO : postBlogPOJOs) {
            postBlogPOJOLongSparseArray.put(postBlogPOJO.getId(), postBlogPOJO);
        }
    }

    private void generateWebView(String themeName, PostBlogPOJO postBlogPOJO) {
        boolean isBookmark = dataBaseHandler.isFavorite(postBlogPOJO.getId());
        dataModel = new DataModelBuilder()
                .withId(postBlogPOJO.getId())
                .withType("blog")
                .withBy(postBlogPOJO.getAuthorName())
                .withTime(postBlogPOJO.getDate())
                .withUrl(postBlogPOJO.getGuid())
                .withDescription(postBlogPOJO.getDescription())
                .withBookmark(isBookmark)
                .withViewed(false)
                .withRank(0)
                .withVoted(false)
                .withPageTitle(postBlogPOJO.getTitle())
                .build();
        new ExtraWebViewCreator()
                .withContext(getActivity())
                .withBookmarkIcon(true)
                .withVoteIcon(false)
                .withThemeName(themeName)
                .withCustomFont("fonts/IRANSansMobile.ttf")
                .withDataModel(dataModel)
                .show();
    }


    public static Fragment newInstance(int typeArticle) {
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(INT_FRAGMENT, typeArticle);
        articleFragment.setArguments(mBundle);
        return articleFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (typeArticle == MainActivity.SAVED_ARTICLE) {
            getPostBlogFromDB();
        }
    }

    @Override
    public void onConnectionChange(ConnectivityEvent event) {
        if (event.getState() == ConnectivityState.CONNECTED) {
            if (typeArticle != MainActivity.SAVED_ARTICLE) {
                circularProgressBar.setVisibility(View.VISIBLE);
                errorConnectionDialog.dismiss();
                preparePostBlog(++pageGlobal);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Register for connectivity changes
        ConnectionBuddy.getInstance().registerForConnectivityEvents(this, this);
    }

    @Override
    public void onStop() {
        // Unregister from connectivity events
        ConnectionBuddy.getInstance().unregisterFromConnectivityEvents(this);
        super.onStop();
    }
}
