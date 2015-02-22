package com.raafstudio.goahead.people.model;

import java.util.ArrayList;

public class Notification {
	int notification_id;
	int user_id;
	String time;
	String message;
	String notification_type;
	int is_read;
	String user_avatar;
	String user_action_id;
	String duration;
	String string_id;
	User user_profile = new User();
	ArrayList<Artwork> detail_artwork = new ArrayList<Artwork>();
	Boolean isNeedToNavigate;
	Boolean is_admin;
	String original;

	public int getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(int notification_id) {
		this.notification_id = notification_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNotification_type() {
		return notification_type;
	}

	public void setNotification_type(String notification_type) {
		this.notification_type = notification_type;
	}

	public int getIs_read() {
		return is_read;
	}

	public void setIs_read(int is_read) {
		this.is_read = is_read;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public String getUser_action_id() {
		return user_action_id;
	}

	public void setUser_action_id(String user_action_id) {
		this.user_action_id = user_action_id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getString_id() {
		return string_id;
	}

	public void setString_id(String string_id) {
		this.string_id = string_id;
	}

	public User getUser_profile() {
		return user_profile;
	}

	public void setUser_profile(User user_profile) {
		this.user_profile = user_profile;
	}

	public ArrayList<Artwork> getDetail_artwork() {
		return detail_artwork;
	}

	public void setDetail_artwork(ArrayList<Artwork> detail_artwork) {
		this.detail_artwork = detail_artwork;
	}

	public Boolean getIsNeedToNavigate() {
		return isNeedToNavigate;
	}

	public void setIsNeedToNavigate(Boolean isNeedToNavigate) {
		this.isNeedToNavigate = isNeedToNavigate;
	}

	public Boolean getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(Boolean is_admin) {
		this.is_admin = is_admin;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

}
