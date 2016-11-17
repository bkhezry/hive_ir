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

package ir.hive.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import ir.hive.R;
import ir.hive.pojo.PostBlogPOJO;
import ir.hive.utility.DateConverter;


public class PostBlogAdapter extends RecyclerView.Adapter<PostBlogAdapter.MyViewHolder> {
    private Context mContext;
    private List<PostBlogPOJO> postBlogPOJOs;
    private DateConverter dateConverter = new DateConverter();
    private Typeface typeface;

    public PostBlogAdapter(Context mContext, List<PostBlogPOJO> postBlogPOJOs) {
        typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/IRANSansMobile.ttf");
        this.mContext = mContext;
        this.postBlogPOJOs = postBlogPOJOs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PostBlogPOJO postBlogPOJO = postBlogPOJOs.get(position);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(postBlogPOJO.getDate() + "000"));
        dateConverter.setGregorianDate(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        holder.date.setText(dateConverter.getIranianDate());
        holder.title.setText(postBlogPOJO.getTitle());
        holder.author.setText(postBlogPOJO.getAuthorName());
        holder.tagContainerLayout.setTags(postBlogPOJO.getTags());
        Glide.with(mContext).load(postBlogPOJO.getMediaDetails()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return postBlogPOJOs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, author;
        ImageView thumbnail;
        TagContainerLayout tagContainerLayout;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            author = (TextView) view.findViewById(R.id.author);
            tagContainerLayout = (TagContainerLayout) view.findViewById(R.id.tagcontainerLayout);
            tagContainerLayout.setTagTypeface(typeface);
        }
    }
}
