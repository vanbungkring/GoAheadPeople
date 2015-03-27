package com.raafstudio.goahead.people.model;

import java.util.ArrayList;

public class User {
	int user_id = 0;
	String profile_id = null;
	String job_profile = "";
	String job_location = "";
	String address = "";
	String city = "";
	String province = "";
	String zip_code = "";
	String phone = "";
	String user_consumer_id = "";
	String fullname = "";
	Boolean is_kol;
	int point;
	String bio = "";
	String last_redeemed_gac = "";
	Boolean is_join_gac;
	int has_confirm_age;
	String last_point_update = "";
	String join_gac_date;
	int av_type;
	String refered_by;
	int done_tutorial;
	int is_enter_market;
	int notifcount;
	String user_avatar;

	int followers = 0;
	int following = 0;
	 
	int is_follow = 0;
	String is_you;

	Boolean isFirstTime = true;
	Boolean isSkipable = true;
	Boolean isNeedToUploadKTP = true;
	int current;
	int fl;
	Account account = new Account();
	ArrayList<Artwork> artworks = new ArrayList<Artwork>() ;
	ArrayList<Product> products = new ArrayList<Product>() ;
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUser_consumer_id() {
		return user_consumer_id;
	}

	public void setUser_consumer_id(String	 user_consumer_id) {
		this.user_consumer_id = user_consumer_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Boolean getIs_kol() {
		return is_kol;
	}

	public void setIs_kol(Boolean is_kol) {
		this.is_kol = is_kol;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getLast_redeemed_gac() {
		return last_redeemed_gac;
	}

	public void setLast_redeemed_gac(String last_redeemed_gac) {
		this.last_redeemed_gac = last_redeemed_gac;
	}

	public Boolean getIs_join_gac() {
		return is_join_gac;
	}

	public void setIs_join_gac(Boolean is_join_gac) {
		this.is_join_gac = is_join_gac;
	}

	public int getHas_confirm_age() {
		return has_confirm_age;
	}

	public void setHas_confirm_age(int has_confirm_age) {
		this.has_confirm_age = has_confirm_age;
	}

	public String getLast_point_update() {
		return last_point_update;
	}

	public void setLast_point_update(String last_point_update) {
		this.last_point_update = last_point_update;
	}

	public String getJoin_gac_date() {
		return join_gac_date;
	}

	public void setJoin_gac_date(String join_gac_date) {
		this.join_gac_date = join_gac_date;
	}

	public int getAv_type() {
		return av_type;
	}

	public void setAv_type(int av_type) {
		this.av_type = av_type;
	}

	public String getRefered_by() {
		return refered_by;
	}

	public void setRefered_by(String refered_by) {
		this.refered_by = refered_by;
	}

	public int getDone_tutorial() {
		return done_tutorial;
	}

	public void setDone_tutorial(int done_tutorial) {
		this.done_tutorial = done_tutorial;
	}

	public int getIs_enter_market() {
		return is_enter_market;
	}

	public void setIs_enter_market(int is_enter_market) {
		this.is_enter_market = is_enter_market;
	}

	public int getNotifcount() {
		return notifcount;
	}

	public void setNotifcount(int notifcount) {
		this.notifcount = notifcount;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public String getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}
 
 

	public int getIs_follow() {
		return is_follow;
	}

	public void setIs_follow(int is_follow) {
		this.is_follow = is_follow;
	}

	public String getIs_you() {
		return is_you;
	}

	public void setIs_you(String is_you) {
		this.is_you = is_you;
	}

	public Boolean getIsFirstTime() {
		return isFirstTime;
	}

	public void setIsFirstTime(Boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}

	public Boolean getIsSkipable() {
		return isSkipable;
	}

	public void setIsSkipable(Boolean isSkipable) {
		this.isSkipable = isSkipable;
	}

	public Boolean getIsNeedToUploadKTP() {
		return isNeedToUploadKTP;
	}

	public void setIsNeedToUploadKTP(Boolean isNeedToUploadKTP) {
		this.isNeedToUploadKTP = isNeedToUploadKTP;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getFl() {
		return fl;
	}

	public void setFl(int fl) {
		this.fl = fl;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ArrayList<Artwork> getArtworks() {
		return artworks;
	}

	public void setArtworks(ArrayList<Artwork> artworks) {
		this.artworks = artworks;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	

}
