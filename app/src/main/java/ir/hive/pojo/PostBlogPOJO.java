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

package ir.hive.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PostBlogPOJO {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("date")
    @Expose
    private Long date;
    @SerializedName("date_gmt")
    @Expose
    private Long dateGmt;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("modified")
    @Expose
    private Long modified;
    @SerializedName("modified_gmt")
    @Expose
    private Long modifiedGmt;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("author")
    @Expose
    private Integer author;
    @SerializedName("author_name")
    @Expose
    private String authorName;
    @SerializedName("featured_media")
    @Expose
    private Integer featuredMedia;
    @SerializedName("media_details")
    @Expose
    private String mediaDetails;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("ping_status")
    @Expose
    private String pingStatus;
    @SerializedName("sticky")
    @Expose
    private Boolean sticky;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("categories")
    @Expose
    private List<String> categories = new ArrayList<>();
    @SerializedName("tags")
    @Expose
    private List<String> tags = new ArrayList<>();

    /**
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The date
     */
    public Long getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     * @return The dateGmt
     */
    public Long getDateGmt() {
        return dateGmt;
    }

    /**
     * @param dateGmt The date_gmt
     */
    public void setDateGmt(Long dateGmt) {
        this.dateGmt = dateGmt;
    }

    /**
     * @return The guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid The guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return The modified
     */
    public Long getModified() {
        return modified;
    }

    /**
     * @param modified The modified
     */
    public void setModified(Long modified) {
        this.modified = modified;
    }

    /**
     * @return The modifiedGmt
     */
    public Long getModifiedGmt() {
        return modifiedGmt;
    }

    /**
     * @param modifiedGmt The modified_gmt
     */
    public void setModifiedGmt(Long modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The author
     */
    public Integer getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Integer author) {
        this.author = author;
    }

    /**
     * @return The authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName The author_name
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * @return The featuredMedia
     */
    public Integer getFeaturedMedia() {
        return featuredMedia;
    }

    /**
     * @param featuredMedia The featured_media
     */
    public void setFeaturedMedia(Integer featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    /**
     * @return The mediaDetails
     */
    public String getMediaDetails() {
        return mediaDetails;
    }

    /**
     * @param mediaDetails The media_details
     */
    public void setMediaDetails(String mediaDetails) {
        this.mediaDetails = mediaDetails;
    }

    /**
     * @return The commentStatus
     */
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     * @param commentStatus The comment_status
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * @return The pingStatus
     */
    public String getPingStatus() {
        return pingStatus;
    }

    /**
     * @param pingStatus The ping_status
     */
    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    /**
     * @return The sticky
     */
    public Boolean getSticky() {
        return sticky;
    }

    /**
     * @param sticky The sticky
     */
    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    /**
     * @return The format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return The categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * @param categories The categories
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return The tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
