package com.raafstudio.goahead.people.model;

import java.util.ArrayList;

public class Artwork {
	String type;
	int id;
	String string_id="";
	int like_stat;
	int comment_stat;
	int view_stat;
	String createdatetime;
	String time;
	String verifydatetime;
	String title;
	String image;
	String image_ori;
	int imageHeight;
	int imageWidth;
	int priority;
	int author_id;
	String sub_type;// artwork_type
	String artwork_class;
	int is_featured;
	String fullname;
	String user_avatar;

	String job_profile;
	String job_location;
	String bio;
	int user_id;
	String link;
	String encrypted_id;

	String artwork_id;

	String featured_image;
	String artwork_content;
	String kind;
	String status;

	String featured_thumb;
	String artwork_thumb;
	String description;
	String publisher_id;
	String artist;
	String convert_status;
	String is_highlight;
	String highlight_from;
	String highlight_until;

	String is_video;
	String is_liked = "FALSE";
	String is_follow;
	String is_you;
	String printerous_pass_token;
	String printerous_token_valid_until;

	ArrayList<Comment> comments = new ArrayList<Comment>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getString_id() {
		return string_id;
	}

	public void setString_id(String string_id) {
		this.string_id = string_id;
	}

	public int getLike_stat() {
		return like_stat;
	}

	public void setLike_stat(int like_stat) {
		this.like_stat = like_stat;
	}

	public int getComment_stat() {
		return comment_stat;
	}

	public void setComment_stat(int comment_stat) {
		this.comment_stat = comment_stat;
	}

	public int getView_stat() {
		return view_stat;
	}

	public void setView_stat(int view_stat) {
		this.view_stat = view_stat;
	}

	public String getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(String createdatetime) {
		this.createdatetime = createdatetime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVerifydatetime() {
		return verifydatetime;
	}

	public void setVerifydatetime(String verifydatetime) {
		this.verifydatetime = verifydatetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage_ori() {
		return image_ori;
	}

	public void setImage_ori(String image_ori) {
		this.image_ori = image_ori;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getSub_type() {
		return sub_type;
	}

	public void setSub_type(String sub_type) {
		this.sub_type = sub_type;
	}

	public String getArtwork_class() {
		return artwork_class;
	}

	public void setArtwork_class(String artwork_class) {
		this.artwork_class = artwork_class;
	}

	public int getIs_featured() {
		return is_featured;
	}

	public void setIs_featured(int is_featured) {
		this.is_featured = is_featured;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public String getIs_you() {
		return is_you;
	}

	public void setIs_you(String is_you) {
		this.is_you = is_you;
	}

	public String getJob_profile() {
		return job_profile;
	}

	public void setJob_profile(String job_profile) {
		this.job_profile = job_profile;
	}

	public String getJob_location() {
		return job_location;
	}

	public void setJob_location(String job_location) {
		this.job_location = job_location;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getEncrypted_id() {
		return encrypted_id;
	}

	public void setEncrypted_id(String encrypted_id) {
		this.encrypted_id = encrypted_id;
	}

	public String getArtwork_id() {
		return artwork_id;
	}

	public void setArtwork_id(String artwork_id) {
		this.artwork_id = artwork_id;
	}

	public String getIs_video() {
		return is_video;
	}

	public void setIs_video(String is_video) {
		this.is_video = is_video;
	}

	public String getFeatured_image() {
		return featured_image;
	}

	public void setFeatured_image(String featured_image) {
		this.featured_image = featured_image;
	}

	public String getArtwork_content() {
		return artwork_content;
	}

	public void setArtwork_content(String artwork_content) {
		this.artwork_content = artwork_content;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFeatured_thumb() {
		return featured_thumb;
	}

	public void setFeatured_thumb(String featured_thumb) {
		this.featured_thumb = featured_thumb;
	}

	public String getArtwork_thumb() {
		return artwork_thumb;
	}

	public void setArtwork_thumb(String artwork_thumb) {
		this.artwork_thumb = artwork_thumb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(String publisher_id) {
		this.publisher_id = publisher_id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getConvert_status() {
		return convert_status;
	}

	public void setConvert_status(String convert_status) {
		this.convert_status = convert_status;
	}

	public String getIs_highlight() {
		return is_highlight;
	}

	public void setIs_highlight(String is_highlight) {
		this.is_highlight = is_highlight;
	}

	public String getHighlight_from() {
		return highlight_from;
	}

	public void setHighlight_from(String highlight_from) {
		this.highlight_from = highlight_from;
	}

	public String getHighlight_until() {
		return highlight_until;
	}

	public void setHighlight_until(String highlight_until) {
		this.highlight_until = highlight_until;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public String getIs_liked() {
		return is_liked;
	}

	public void setIs_liked(String is_liked) {
		this.is_liked = is_liked;
	}

	public String getIs_follow() {
		return is_follow;
	}

	public void setIs_follow(String is_follow) {
		this.is_follow = is_follow;
	}

	public String getPrinterous_pass_token() {
		return printerous_pass_token;
	}

	public void setPrinterous_pass_token(String printerous_pass_token) {
		this.printerous_pass_token = printerous_pass_token;
	}

	public String getPrinterous_token_valid_until() {
		return printerous_token_valid_until;
	}

	public void setPrinterous_token_valid_until(
			String printerous_token_valid_until) {
		this.printerous_token_valid_until = printerous_token_valid_until;
	}

}
