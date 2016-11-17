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

package ir.hive.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;


public class DataBaseHandler {
    private Context context;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DataBaseHandler(Context context) {
        this.context = context;
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "hive-db",
                null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public void removeBookmark(Long id) {
        ArticleDao articleDao = daoSession.getArticleDao();
        articleDao.deleteByKey(id);
    }

    public void addBookmark(Article article) {
        ArticleDao articleDao = daoSession.getArticleDao();
        articleDao.insert(article);
    }


    public List<Article> getArticles() {
        ArticleDao articleDao = daoSession.getArticleDao();
        return articleDao.loadAll();
    }

    public boolean isFavorite(Long id) {
        ArticleDao articleDao = daoSession.getArticleDao();
        Article article = articleDao.load(id);
        return article != null;
    }
}
