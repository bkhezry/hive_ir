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

package ir.hive.utility;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ir.hive.dao.Article;
import ir.hive.pojo.PostBlogPOJO;


public class Converter {
    public static int dpToPx(int dp, Resources resources) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }

    public static Article PostBlogToArticle(PostBlogPOJO postBlogPOJO) {
        Article article = new Article();
        article.setAuthor(postBlogPOJO.getAuthor());
        article.setAuthorName(postBlogPOJO.getAuthorName());
        article.setCommentStatus(postBlogPOJO.getCommentStatus());
        article.setDate(postBlogPOJO.getDate());
        article.setDateGmt(postBlogPOJO.getDateGmt());
        article.setDescription(postBlogPOJO.getDescription());
        article.setTitle(postBlogPOJO.getTitle());
        article.setFeaturedMedia(postBlogPOJO.getFeaturedMedia());
        article.setFormat(postBlogPOJO.getFormat());
        article.setGuid(postBlogPOJO.getGuid());
        article.setId(postBlogPOJO.getId());
        article.setType(postBlogPOJO.getType());
        article.setMediaDetails(postBlogPOJO.getMediaDetails());
        article.setSticky(postBlogPOJO.getSticky());
        article.setPingStatus(postBlogPOJO.getPingStatus());
        article.setLink(postBlogPOJO.getLink());
        article.setModified(postBlogPOJO.getModified());
        article.setModifiedGmt(postBlogPOJO.getModifiedGmt());
        article.setTags(toStringList(postBlogPOJO.getTags()));
        return article;
    }

    private static PostBlogPOJO ArticleToPostBlog(Article article) {
        PostBlogPOJO postBlogPOJO = new PostBlogPOJO();
        postBlogPOJO.setAuthor(article.getAuthor());
        postBlogPOJO.setAuthorName(article.getAuthorName());
        postBlogPOJO.setCommentStatus(article.getCommentStatus());
        postBlogPOJO.setDate(article.getDate());
        postBlogPOJO.setDateGmt(article.getDateGmt());
        postBlogPOJO.setDescription(article.getDescription());
        postBlogPOJO.setTitle(article.getTitle());
        postBlogPOJO.setFeaturedMedia(article.getFeaturedMedia());
        postBlogPOJO.setFormat(article.getFormat());
        postBlogPOJO.setGuid(article.getGuid());
        postBlogPOJO.setId(article.getId());
        postBlogPOJO.setType(article.getType());
        postBlogPOJO.setMediaDetails(article.getMediaDetails());
        postBlogPOJO.setSticky(article.getSticky());
        postBlogPOJO.setPingStatus(article.getPingStatus());
        postBlogPOJO.setLink(article.getLink());
        postBlogPOJO.setModified(article.getModified());
        postBlogPOJO.setModifiedGmt(article.getModifiedGmt());
        postBlogPOJO.setTags(toListString(article.getTags()));
        return postBlogPOJO;
    }

    public static List<PostBlogPOJO> ArticlesToPostBlog(List<Article> articles) {
        List<PostBlogPOJO> postBlogPOJOs = new ArrayList<>();
        for (Article article : articles) {
            postBlogPOJOs.add(ArticleToPostBlog(article));
        }
        return postBlogPOJOs;
    }

    @Nullable
    private static String toStringList(List<String> tagList) {
        String tags = "";
        for (String s : tagList) {
            if (tags.equals("")) {
                tags = s;
            } else {
                tags += "," + s;
            }
        }
        return tags;

    }

    private static List<String> toListString(String tags) {
        List<String> tagList = new ArrayList<>();
        if (!tags.equals("")) {
            String[] splits = tags.split(",");
            Collections.addAll(tagList, splits);
        }
        return tagList;
    }
}
