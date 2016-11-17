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

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(
        active = true
)
public class Article {
    @Id
    private Long id;
    private Long date;
    private Long dateGmt;
    private String guid;
    private Long modified;
    private Long modifiedGmt;
    private String type;
    private String link;
    private String title;
    private String description;
    private Integer author;
    private String authorName;
    private Integer featuredMedia;
    private String mediaDetails;
    private String commentStatus;
    private String pingStatus;
    private Boolean sticky;
    private String format;
    private Integer createTime;
    private String tags;
/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;
/** Used for active entity operations. */
@Generated(hash = 434328755)
private transient ArticleDao myDao;
@Generated(hash = 1109235672)
public Article(Long id, Long date, Long dateGmt, String guid, Long modified,
        Long modifiedGmt, String type, String link, String title,
        String description, Integer author, String authorName,
        Integer featuredMedia, String mediaDetails, String commentStatus,
        String pingStatus, Boolean sticky, String format, Integer createTime,
        String tags) {
    this.id = id;
    this.date = date;
    this.dateGmt = dateGmt;
    this.guid = guid;
    this.modified = modified;
    this.modifiedGmt = modifiedGmt;
    this.type = type;
    this.link = link;
    this.title = title;
    this.description = description;
    this.author = author;
    this.authorName = authorName;
    this.featuredMedia = featuredMedia;
    this.mediaDetails = mediaDetails;
    this.commentStatus = commentStatus;
    this.pingStatus = pingStatus;
    this.sticky = sticky;
    this.format = format;
    this.createTime = createTime;
    this.tags = tags;
}
@Generated(hash = 742516792)
public Article() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public Long getDate() {
    return this.date;
}
public void setDate(Long date) {
    this.date = date;
}
public Long getDateGmt() {
    return this.dateGmt;
}
public void setDateGmt(Long dateGmt) {
    this.dateGmt = dateGmt;
}
public String getGuid() {
    return this.guid;
}
public void setGuid(String guid) {
    this.guid = guid;
}
public Long getModified() {
    return this.modified;
}
public void setModified(Long modified) {
    this.modified = modified;
}
public Long getModifiedGmt() {
    return this.modifiedGmt;
}
public void setModifiedGmt(Long modifiedGmt) {
    this.modifiedGmt = modifiedGmt;
}
public String getType() {
    return this.type;
}
public void setType(String type) {
    this.type = type;
}
public String getLink() {
    return this.link;
}
public void setLink(String link) {
    this.link = link;
}
public String getTitle() {
    return this.title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getDescription() {
    return this.description;
}
public void setDescription(String description) {
    this.description = description;
}
public Integer getAuthor() {
    return this.author;
}
public void setAuthor(Integer author) {
    this.author = author;
}
public String getAuthorName() {
    return this.authorName;
}
public void setAuthorName(String authorName) {
    this.authorName = authorName;
}
public Integer getFeaturedMedia() {
    return this.featuredMedia;
}
public void setFeaturedMedia(Integer featuredMedia) {
    this.featuredMedia = featuredMedia;
}
public String getMediaDetails() {
    return this.mediaDetails;
}
public void setMediaDetails(String mediaDetails) {
    this.mediaDetails = mediaDetails;
}
public String getCommentStatus() {
    return this.commentStatus;
}
public void setCommentStatus(String commentStatus) {
    this.commentStatus = commentStatus;
}
public String getPingStatus() {
    return this.pingStatus;
}
public void setPingStatus(String pingStatus) {
    this.pingStatus = pingStatus;
}
public Boolean getSticky() {
    return this.sticky;
}
public void setSticky(Boolean sticky) {
    this.sticky = sticky;
}
public String getFormat() {
    return this.format;
}
public void setFormat(String format) {
    this.format = format;
}
public Integer getCreateTime() {
    return this.createTime;
}
public void setCreateTime(Integer createTime) {
    this.createTime = createTime;
}
public String getTags() {
    return this.tags;
}
public void setTags(String tags) {
    this.tags = tags;
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}
/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 2112142041)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getArticleDao() : null;
}
}
