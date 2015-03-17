package com.raafstudio.goahead.people.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.raaf.rDialog;
import com.raaf.rNet;
import com.raaf.http.rLoader;
import com.raaf.http.rLoader.Http_type;
import com.raaf.io.rFile;
import com.raafstudio.goahead.people.model.Artwork;
import com.raafstudio.goahead.people.model.Brand;
import com.raafstudio.goahead.people.model.City;
import com.raafstudio.goahead.people.model.Comment;
import com.raafstudio.goahead.people.model.Meta;
import com.raafstudio.goahead.people.model.Notification;
import com.raafstudio.goahead.people.model.Product;
import com.raafstudio.goahead.people.model.Province;
import com.raafstudio.goahead.people.model.SubBrand;
import com.raafstudio.goahead.people.model.User;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class API {

	static List<NameValuePair> params;

	public static Boolean cekInet(Context ctx) {
		if (!rNet.isNetworkConnected(ctx)) {
			rDialog.SetToast(ctx, "cannot connect internet");
			return false;
		} else
			return true;

	}

	public static void getMeta(String json) {
		so.meta = new Meta();
		so.meta.setJson(json);
		try {
			JSONObject jObject = new JSONObject(json);
			jObject = jObject.getJSONObject("meta");
			so.meta.setCode(jObject.getInt("code"));
			so.meta.setErrorType(jObject.getString("errorType"));
			so.meta.setErrorDetail(jObject.getString("errorDetail"));

		} catch (JSONException e) {
			so.meta.setCode(-1);
			so.meta.setErrorType("network");
			so.meta.setErrorDetail(e.getMessage());
		} finally {
			// dialog.CloseProgressDialog();
		}
	}

	public static void doLogin(String username, String password,
			String device_token, Handler handler) {
		String uri = so.getApiUrl() + "login/UserLogin";
		uri += "?username=" + username;
		uri += "&password=" + password;
		uri += "&device_token=" + device_token;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_login);
		b.putInt("mode", 1);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void doLogOut(Handler handler) {
		String uri = so.getApiUrl() + "login/logout";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("token", so.getToken()));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_login);
		b.putInt("mode", 2);
		rLoader loader = new rLoader(uri, handler, b, Http_type.post, params,
				"");
		loader.start();
	}

	public static void getNotifiation(int limit, int offset, Handler handler) {
		String uri = so.getApiUrl() + "notification";
		uri += "?token=" + so.getToken();
		uri += "&limit=" + limit;
		uri += "&offset=" + offset;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_notification);
		b.putInt("mode", 1);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void getProfile(int profile_id, Handler handler) {
		String uri = so.getApiUrl() + "profile/get_profile";
		uri += "?token=" + so.getToken();
		uri += "&profile_id=" + profile_id;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_base);
		b.putInt("mode", 1);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void getProfileArtwork(int profile_id, int limit, int offset,
			Handler handler) {
		String uri = so.getApiUrl() + "profile/get_profile_artwork";
		uri += "?token=" + so.getToken();
		uri += "&profile_id=" + profile_id;
		uri += "&limit=" + limit;
		uri += "&offset=" + offset;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_profile);
		b.putInt("mode", 2);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void getProfileProduct(int profile_id, int limit, int offset,
			Handler handler) {
		String uri = so.getApiUrl() + "profile/get_profile_product";
		uri += "?token=" + so.getToken();
		uri += "&profile_id=" + profile_id;
		uri += "&limit=" + limit;
		uri += "&offset=" + offset;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_profile);
		b.putInt("mode", 3);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void getProfileFollower(int profile_id, int limit,
			int offset, Handler handler) {
		String uri = so.getApiUrl() + "profile/my_followers";
		uri += "?token=" + so.getToken();
		uri += "&profile_id=" + profile_id;
		uri += "&limit=" + limit;
		uri += "&offset=" + offset;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_profile);
		b.putInt("mode", 4);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void getProfileFollowing(int profile_id, int limit,
			int offset, Handler handler) {
		String uri = so.getApiUrl() + "profile/my_following";
		uri += "?token=" + so.getToken();
		uri += "&profile_id=" + profile_id;
		uri += "&limit=" + limit;
		uri += "&offset=" + offset;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_profile);
		b.putInt("mode", 5);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void doProfileFollow(String profile_id, Handler handler) {
		String uri = so.getApiUrl() + "profile/follow";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("profile_id", profile_id));
		params.add(new BasicNameValuePair("token", so.getToken()));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_profile);
		b.putInt("mode", 6);
		rLoader loader = new rLoader(uri, handler, b, Http_type.post, params,
				"");
		loader.start();
	}

	public static void SettingEditProfile(String bio, String job_profile,
			String job_location, rFile user_avatar, Handler handler) {
		String uri = so.getApiUrl() + "profile/editprofile";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("bio", bio));
		params.add(new BasicNameValuePair("job_profile", job_profile));
		params.add(new BasicNameValuePair("job_location", job_location));
		params.add(new BasicNameValuePair("token", so.getToken()));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_setting);
		b.putInt("mode", 1);

		rLoader loader;
		if (so.apply_image)
			loader = new rLoader(uri, handler, b, Http_type.multipart, params,
					user_avatar);
		else
			loader = new rLoader(uri, handler, b, Http_type.post, params, "");
		loader.start();
	}

	public static void SettingGetProfile(Handler handler) {
		String uri = so.getApiUrl() + "profile/dataProfile";
		uri += "?token=" + so.getToken();
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_setting);
		b.putInt("mode", 2);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void SettingCekToken(Handler handler) {
		String uri = so.getApiUrl() + "general/checkToken";
		uri += "?token=" + so.getToken();
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_setting);
		b.putInt("mode", 3);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void SettingGetUserAccount(Handler handler) {
		String uri = so.getApiUrl() + "user/userdataaccount";
		uri += "?token=" + so.getToken();
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_setting);
		b.putInt("mode", 4);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void SettingUpdateAccount(String deviceid,
			String mobilephone, String province, String city, String brand,
			String subbrand, String RegistrationID, Handler handler) {
		String uri = so.getApiUrl() + "user/update";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("token", so.getToken()));
		params.add(new BasicNameValuePair("deviceid", deviceid));
		params.add(new BasicNameValuePair("mobilephone", mobilephone));
		params.add(new BasicNameValuePair("province", province));
		params.add(new BasicNameValuePair("city", city));
		params.add(new BasicNameValuePair("brand", brand));
		params.add(new BasicNameValuePair("subbrand", subbrand));
		params.add(new BasicNameValuePair("RegistrationID", RegistrationID));

		Bundle b = new Bundle();
		b.putInt("modul", so.modul_setting);
		b.putInt("mode", 5);
		rLoader loader = new rLoader(uri, handler, b, Http_type.post, params,
				"");
		loader.start();
	}

	public static void SettingUploadKtp(String KTPImage, String deviceid,
			String FileType, Handler handler) {
		String uri = so.getApiUrl() + "user/upload_ktp";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("token", so.getToken()));
		params.add(new BasicNameValuePair("deviceid", deviceid));
		params.add(new BasicNameValuePair("FileType", FileType));

		params.add(new BasicNameValuePair("KTPImage", KTPImage));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_setting);
		b.putInt("mode", 6);
		rLoader loader = new rLoader(uri, handler, b, Http_type.post, params,
				"");
		loader.start();
	}

	public static void StoreGet(String store_type, int limit, int offset,
			Handler handler) {
		String uri = so.getApiUrl() + "store/getStore";
		uri += "?token=" + so.getToken();
		uri += "&store_type=" + store_type;
		uri += "&limit=" + limit;
		uri += "&offset=" + offset;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_store);
		b.putInt("mode", 1);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void StoreGetDetail(String store_id, Handler handler) {
		String uri = so.getApiUrl() + "store/getDetailStore";
		uri += "?token=" + so.getToken();
		uri += "&id=" + store_id;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_store);
		b.putInt("mode", 2);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void StoreHome(Handler handler) {
		String uri = so.getApiUrl() + "general/home";
		uri += "?token=" + so.getToken();
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_store);
		b.putInt("mode", 3);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void StoreRedem(String store_id, Handler handler) {
		String uri = so.getApiUrl() + "store/redeemStore";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("token", so.getToken()));
		params.add(new BasicNameValuePair("id", store_id));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_store);
		b.putInt("mode", 4);
		rLoader loader = new rLoader(uri, handler, b, Http_type.post, params,
				"");
		loader.start();
	}

	public static void DiscoverGet(String Category, int offset, Handler handler) {
		String uri = so.getApiUrl() + "discover";
		uri += "?token=" + so.getToken();
		uri += "&category=" + Category;
		uri += "&offset=" + offset;
		uri += "&mode=";
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_discover);
		b.putInt("mode", 1);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void DiscoverGetArtwork(String string_id, Handler handler) {
		String uri = so.getApiUrl() + "artwork";
		uri += "?token=" + so.getToken();
		uri += "&string_id=" + string_id;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_discover);
		b.putInt("mode", 2);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void DiscoverGetArtworkComment(String string_id, int page,
			Handler handler) {
		String uri = so.getApiUrl() + "artwork/getMoreComment";
		uri += "?token=" + so.getToken();
		uri += "&page=" + page;
		uri += "&string_id=" + string_id;
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_discover);
		b.putInt("mode", 3);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void DiscoverPostArtwork(rFile file, String title,
			String desc, String artType, Handler handler) {
		String uri = so.getApiUrl() + "artwork/ArtworkInsert";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("token", so.getToken()));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("desc", desc));
		params.add(new BasicNameValuePair("artType", artType));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_discover);
		b.putInt("mode", 4);
		rLoader loader = new rLoader(uri, handler, b, Http_type.multipart,
				params, file);
		loader.start();
	}

	public static void DiscoverPostArtworkComment(String art_id,
			String comment, Handler handler) {
		String uri = so.getApiUrl() + "artwork/postCommentArtwork";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("token", so.getToken()));
		params.add(new BasicNameValuePair("art_id", art_id));
		params.add(new BasicNameValuePair("comment", comment));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_discover);
		b.putInt("mode", 5);
		rLoader loader = new rLoader(uri, handler, b, Http_type.post, params,
				"");
		loader.start();
	}

	public static void DiscoverLikeArtwork(String art_id, Handler handler) {
		String uri = so.getApiUrl() + "artwork/like";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("token", so.getToken()));
		params.add(new BasicNameValuePair("art_id", art_id));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_discover);
		b.putInt("mode", 6);
		b.putString("art_id", art_id);
		rLoader loader = new rLoader(uri, handler, b, Http_type.post, params,
				"");
		loader.start();
	}

	public static void DiscoverReportArtwork(String report_id,
			String artwork_id, String content, Handler handler) {
		String uri = so.getApiUrl() + "user/report_artwork";
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("token", so.getToken()));
		params.add(new BasicNameValuePair("report_id", report_id));
		params.add(new BasicNameValuePair("artwork_id", artwork_id));
		params.add(new BasicNameValuePair("content", content));
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_discover);
		b.putInt("mode", 7);
		rLoader loader = new rLoader(uri, handler, b, Http_type.post, params,
				"");
		loader.start();
	}

	public static void BrandList(Handler handler) {
		String uri = so.getApiUrl() + "registerdata/brandParents";
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_brand);
		b.putInt("mode", 1);
		rLoader loader = new rLoader(uri, handler, b, Http_type.get, null, "");
		loader.start();
	}

	public static void BrandListSub(Handler handler) {
		String uri = so.getApiUrl() + "registerdata/subBrand";
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_brand);
		b.putInt("mode", 2);
		rLoader loader = new rLoader(uri, handler, b, Http_type.get, null, "");
		loader.start();
	}

	public static void BrandProvince(Handler handler) {
		String uri = so.getApiUrl() + "registerdata/provinsi";
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_brand);
		b.putInt("mode", 3);
		rLoader loader = new rLoader(uri, handler, b, Http_type.get, null, "");
		loader.start();
	}

	public static void BrandCity(Handler handler) {
		String uri = so.getApiUrl() + "registerdata/city";
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_brand);
		b.putInt("mode", 4);
		rLoader loader = new rLoader(uri, handler, b, Http_type.get, null, "");
		loader.start();
	}

	public static void MenuGetUser(Handler handler) {
		String uri = so.getApiUrl() + "user";
		uri += "?token=" + so.getToken();
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_menu);
		b.putInt("mode", 1);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void MenuGetEarnings(Handler handler) {
		String uri = so.getApiUrl() + "marketplace/earnings";
		uri += "?token=" + so.getToken();
		Bundle b = new Bundle();
		b.putInt("modul", so.modul_menu);
		b.putInt("mode", 2);
		rLoader loader = new rLoader(uri, handler, b);
		loader.start();
	}

	public static void ApiParser(Message msg) {
		String message = msg.getData().getString("message");
		if (message.contains("\"meta\"")) {
			API.getMeta(message);
			int modul = msg.getData().getInt("modul");
			int mode = msg.getData().getInt("mode");
			so.meta.setMode(mode);
			so.meta.setModul(modul);
			if (so.meta.getCode() != 200)
				return;

			JSONObject jo, jo1;
			JSONArray jar = null, jar1 = null;
			try {
				jo = new JSONObject(message);

				try {
					jo = jo.getJSONObject("response");
				} catch (Exception e) {
					try {
						jar = jo.getJSONArray("response");

					} catch (Exception e1) {

					}
				}

				switch (modul) {
				case so.modul_notification:
					jar = jo.getJSONArray("notifications");
					for (int i = 0; i < jar.length(); i++) {
						jo = jar.getJSONObject(i);
						Notification notif = new Notification();
						notif.setNotification_id(jo.getInt("notification_id"));
						notif.setUser_id(jo.getInt("user_id"));
						notif.setMessage(jo.getString("message"));
						notif.setNotification_type(jo
								.getString("notification_type"));
						notif.setIs_read(jo.getInt("is_read"));
						notif.setUser_avatar(jo.getString("user_avatar"));
						notif.setUser_action_id(jo.getString("user_action_id"));
						notif.setDuration(jo.getString("duration"));
						notif.setOriginal(jo.getString("original"));

						if (jo.has("string_id")) {
							notif.setString_id(jo.getString("string_id"));
							notif.setIsNeedToNavigate(jo
									.getBoolean("isNeedToNavigate"));
							notif.setIs_admin(jo.getBoolean("is_admin"));
							if (!jo.isNull("detail_artwork")) {
								jar1 = jo.getJSONArray("detail_artwork");
								jo1 = jo.getJSONObject("user_profile");
								notif.getUser_profile().setUser_id(
										jo1.getInt("user_id"));
								notif.getUser_profile().setFullname(
										jo1.getString("fullname"));
								notif.getUser_profile().setJob_profile(
										jo1.getString("job_profile"));
								notif.getUser_profile().setJob_location(
										jo1.getString("job_location"));
								notif.getUser_profile().setBio(
										jo1.getString("bio"));
								notif.getUser_profile().setProfile_id(
										jo1.getString("encrypted_id"));
								for (int j = 0; j < jar1.length(); j++) {
									jo1 = jar1.getJSONObject(j);
									Artwork art = new Artwork();

									art.setType(jo1.getString("type"));
									art.setString_id(jo1.getString("string_id"));
									art.setLike_stat(jo1.getInt("like_stat"));
									art.setComment_stat(jo1
											.getInt("comment_stat"));
									art.setView_stat(jo1.getInt("view_stat"));
									art.setCreatedatetime(jo1
											.getString("createdatetime"));
									art.setTime(jo1.getString("time"));
									art.setVerifydatetime(jo1
											.getString("verifydatetime"));
									art.setTitle(jo1.getString("title"));
									art.setImage(jo1.getString("image"));
									art.setImage_ori(jo1.getString("image_ori"));
									art.setImageHeight(jo1
											.getInt("imageHeight"));
									art.setImageWidth(jo1.getInt("imageWidth"));
									art.setPriority(jo1.getInt("priority"));
									art.setAuthor_id(jo1.getInt("author_id"));
									art.setSub_type(jo1.getString("sub_type"));
									art.setArtwork_class(jo1
											.getString("artwork_class"));
									art.setIs_featured(jo1
											.getInt("is_featured"));
									art.setFullname(jo1.getString("fullname"));
									art.setUser_avatar(jo1
											.getString("user_avatar"));
									art.setIs_you(jo1.getString("is_you"));
									art.setJob_profile(jo1
											.getString("job_profile"));
									art.setJob_location(jo1
											.getString("job_location"));
									art.setBio(jo1.getString("bio"));
									art.setUser_id(jo1.getInt("user_id"));
									art.setLink(jo1.getString("link"));
									art.setEncrypted_id(jo1
											.getString("encrypted_id"));
									notif.getDetail_artwork().add(art);
								}
							}
						}
						so.getNotifications().add(notif);
					}
					break;
				case so.modul_login:
					switch (mode) {
					case 1:// login
						if (so.meta.getCode() == 200) {
							JSONObject jObject;
							try {

								jObject = new JSONObject(message);
								jObject = jObject.getJSONObject("response");
								so.setToken(jObject.getString("AuthToken"));
								so.getUser().setIsFirstTime(
										jObject.getBoolean("isFirstTime"));
								so.getUser().setIsSkipable(
										jObject.getBoolean("isSkipable"));
								so.getUser()
										.setIsNeedToUploadKTP(
												jObject.getBoolean("isNeedToUploadKTP"));

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						break;
					case 2:// logout

						break;
					}
					break;
				case so.modul_menu:
					switch (mode) {
					case 1:// user info
						so.getUser().setUser_id(jo.getInt("user_id"));
						so.getUser()
								.setJob_profile(jo.getString("job_profile"));
						so.getUser().setJob_location(
								jo.getString("job_location"));
						so.getUser().setAddress(jo.getString("address"));
						so.getUser().setCity(jo.getString("city"));
						so.getUser().setProvince(jo.getString("province"));
						so.getUser().setZip_code(jo.getString("zip_code"));
						so.getUser().setPhone(jo.getString("phone"));
						so.getUser().setUser_consumer_id(
								jo.getInt("user_consumer_id"));
						so.getUser().setFullname(jo.getString("fullname"));
						so.getUser().setIs_kol(jo.getBoolean("is_kol"));
						so.getUser().setPoint(jo.getInt("point"));
						so.getUser().setBio(jo.getString("bio"));
						so.getUser().setLast_redeemed_gac(
								jo.getString("last_redeemed_gac"));
						so.getUser().setIs_join_gac(
								jo.getBoolean("is_join_gac"));
						so.getUser().setHas_confirm_age(
								jo.getInt("has_confirm_age"));
						so.getUser().setLast_point_update(
								jo.getString("last_point_update"));
						so.getUser().setJoin_gac_date(
								jo.getString("join_gac_date"));
						so.getUser().setAv_type(jo.getInt("av_type"));
						so.getUser().setRefered_by(jo.getString("refered_by"));
						so.getUser().setDone_tutorial(
								jo.getInt("done_tutorial"));
						so.getUser().setIs_enter_market(
								jo.getInt("is_enter_market"));
						so.getUser().setNotifcount(jo.getInt("notifcount"));
						so.getUser()
								.setUser_avatar(jo.getString("user_avatar"));
						break;
					case 2:// earnings info

						break;
					}
					break;
				case so.modul_base:

					so.user1 = null;
					so.getUserOther().setJob_profile(
							jo.getString("job_profile"));
					so.getUserOther().setJob_location(
							jo.getString("job_location"));
					so.getUserOther().setAddress(jo.getString("address"));
					so.getUserOther().setCity(jo.getString("city"));
					so.getUserOther().setProvince(jo.getString("province"));
					so.getUserOther().setZip_code(jo.getString("zip_code"));
					so.getUserOther().setPhone(jo.getString("phone"));
					so.getUserOther().setUser_consumer_id(
							jo.getInt("user_consumer_id"));
					so.getUserOther().setFullname(jo.getString("fullname"));
					so.getUserOther().setUser_avatar(
							jo.getString("user_avatar"));
					so.getUserOther().setIs_kol(jo.getBoolean("is_kol"));
					so.getUserOther().setPoint(jo.getInt("point"));
					so.getUserOther().setBio(jo.getString("bio"));
					so.getUserOther().setLast_redeemed_gac(
							jo.getString("last_redeemed_gac"));
					so.getUserOther().setIs_join_gac(
							jo.getBoolean("is_join_gac"));
					so.getUserOther().setHas_confirm_age(
							jo.getInt("has_confirm_age"));
					so.getUserOther().setLast_point_update(
							jo.getString("last_point_update"));
					so.getUserOther().setJoin_gac_date(
							jo.getString("join_gac_date"));
					so.getUserOther().setAv_type(jo.getInt("av_type"));
					so.getUserOther().setProfile_id(jo.getString("user_id"));
					so.getUserOther().setFollowers(jo.getInt("followers"));
					so.getUserOther().setFollowing(jo.getInt("following"));
					so.getUserOther().setIs_follow(jo.getInt("is_follow"));
					so.getUserOther().setIs_you(jo.getString("is_you"));
					jo = new JSONObject(message);
					so.getUserOther().setUser_id(jo.getInt("profile_id"));

					break;
				case so.modul_profile:
					switch (mode) {

					case 2:// profile artwork
						for (int i = 0; i < jar.length(); i++) {
							jo = jar.getJSONObject(i);
							Artwork art = new Artwork();
							String artwork_id = jo.getString("id");
							art.setArtwork_id(artwork_id);
							art.setView_stat(jo.getInt("view_stat"));
							art.setComment_stat(jo.getInt("comment_stat"));
							art.setLike_stat(jo.getInt("like_stat"));
							art.setCreatedatetime(jo.getString("date"));
							art.setIs_video(jo.getString("is_video"));
							art.setImage_ori(jo.getString("featured_image"));
							art.setImage(jo.getString("image"));
							art.setUser_avatar(jo.getString("user_avatar"));
							art.setArtwork_content(jo
									.getString("artwork_content"));
							art.setString_id(jo.getString("string_id"));
							art.setAuthor_id(jo.getInt("artwork_author_id"));
							art.setKind(jo.getString("kind"));
							art.setTitle(jo.getString("title"));
							art.setType(jo.getString("artwork_type"));
							art.setStatus(jo.getString("status"));
							art.setFullname(jo.getString("fullname"));
							art.setJob_location(jo.getString("job_location"));
							art.setJob_profile(jo.getString("job_profile"));
							art.setBio(jo.getString("bio"));
							art.setId(jo.getInt("artwork_id"));
							art.setUser_id(jo.getInt("user_id"));
							art.setEncrypted_id(jo.getString("encrypted_id"));
							Boolean found = false;
							for (Artwork art1 : so.getUserOther().getArtworks()) {
								if (art1.getArtwork_id().equals(artwork_id)) {
									found = true;
									art1 = art;
								}
							}
							if (!found)
								so.getUserOther().getArtworks().add(art);
						}
						break;
					case 3:// profile product
						if (jar != null) {
							for (int i = 0; i < jar.length(); i++) {
								jo = jar.getJSONObject(i);
								Product pro = new Product();
								pro.setEncrypted_id(jo.getString("product_id"));
								pro.setString_id(jo.getString("string_id"));
								pro.setProduct_title(jo
										.getString("product_title"));
								pro.setProduct_description(jo
										.getString("product_description"));
								pro.setProduct_image(jo
										.getString("product_image"));
								pro.setProduct_thumbnail(jo
										.getString("product_thumbnail"));
								pro.setUser_id(jo.getInt("user_id"));
								pro.setPrice(jo.getString("price"));
								pro.setStock(jo.getInt("stock"));
								pro.setCategory_id(jo.getInt("category_id"));
								pro.setSubmitted_by(jo
										.getString("submitted_by"));
								pro.setPurchase_type(jo
										.getString("purchase_type"));
								pro.setPublisher_id(jo.getInt("publisher_id"));
								pro.setProduct_respawn(jo
										.getString("product_respawn"));
								pro.setIs_published(jo
										.getString("is_published"));
								pro.setVerifydatetime(jo
										.getString("verifydatetime"));
								pro.setPost_type(jo.getString("post_type"));
								pro.setIs_highlighted(jo
										.getString("is_highlighted"));
								pro.setHighlight_from(jo
										.getString("highlight_from"));
								pro.setHighlight_until(jo
										.getString("highlight_until"));
								pro.setCreatedatetime(jo
										.getString("createdatetime"));
								pro.setBrand(jo.getString("brand"));
								pro.setBrand_url(jo.getString("brand_url"));
								pro.setPrinterous_product_id(jo
										.getString("printerous_product_id"));
								pro.setImage(jo.getString("image"));
								pro.setFullname(jo.getString("fullname"));
								pro.setCount(jo.getInt("count"));
								pro.setView_stat(jo.getInt("view_stat"));
								pro.setType(jo.getString("type"));
								pro.setLike_stat(jo.getInt("like_stat"));
								pro.setIs_want(jo.getString("is_want"));
								pro.setLike_count(jo.getInt("like_count"));
								so.getUserOther().getProducts().add(pro);
							}
						}
						break;
					case 4:// follower

						break;
					case 5:// following

						break;
					case 6:// follow
						if (so.getUserOther().getIs_follow() == 1)
							so.getUserOther().setIs_follow(0);
						else
							so.getUserOther().setIs_follow(1);
						break;
					}
					break;
				case so.modul_discover:
					switch (mode) {
					case 1:// get discover
						for (int i = 0; i < jar.length(); i++) {
							jo = jar.getJSONObject(i);
							Artwork art = new Artwork();
							art.setType(jo.getString("type"));
							art.setString_id(jo.getString("string_id"));
							art.setLike_stat(jo.getInt("like_stat"));
							art.setComment_stat(jo.getInt("comment_stat"));
							art.setView_stat(jo.getInt("view_stat"));
							art.setCreatedatetime(jo
									.getString("createdatetime"));
							art.setTime(jo.getString("time"));
							art.setVerifydatetime(jo
									.getString("verifydatetime"));
							art.setTitle(jo.getString("title"));
							art.setImage(jo.getString("image"));
							art.setImage_ori(jo.getString("image_ori"));
							art.setImageHeight(jo.getInt("imageHeight"));
							art.setImageWidth(jo.getInt("imageWidth"));
							art.setPriority(jo.getInt("priority"));
							art.setAuthor_id(jo.getInt("author_id"));
							art.setSub_type(jo.getString("sub_type"));
							art.setArtwork_class(jo.getString("artwork_class"));
							art.setIs_featured(jo.getInt("is_featured"));
							art.setFullname(jo.getString("fullname"));
							art.setUser_avatar(jo.getString("user_avatar"));
							art.setIs_you(jo.getString("is_you"));
							art.setJob_profile(jo.getString("job_profile"));
							art.setJob_location(jo.getString("job_location"));
							art.setBio(jo.getString("bio"));
							if (jo.isNull("user_id"))
								art.setUser_id(jo.getInt("author_id"));
							else
								art.setUser_id(jo.getInt("user_id"));
							art.setLink(jo.getString("link"));
							art.setArtwork_id(jo.getString("id"));
							so.getDiscoverArtworks().add(art);
						}

						break;
					case 2:// get discover artwork
						jo1 = jo.getJSONObject("userart");
						String string_id = jo1.getString("string_id");
						Boolean found = false;
						Artwork art = new Artwork();
						art.setArtwork_id(jo1.getString("artwork_id"));
						art.setTitle(jo1.getString("artwork_title"));
						art.setArtwork_thumb("http://staging.goaheadpeople.com/media/submit/"
								+ jo1.getString("artwork_thumb"));
						art.setCreatedatetime(jo1.getString("createdatetime"));
						art.setVerifydatetime(jo1.getString("verifydatetime"));
						art.setArtwork_class(jo1.getString("artwork_class"));
						art.setStatus(jo1.getString("status"));
						art.setDescription(jo1.getString("artwork_description"));
						art.setAuthor_id(jo1.getInt("artwork_author_id"));
						art.setType(jo1.getString("artwork_type"));
						art.setIs_video(jo1.getString("is_video"));
						art.setPrinterous_pass_token(jo1
								.getString("printerous_pass_token"));
						art.setPrinterous_token_valid_until(jo1
								.getString("printerous_token_valid_until"));
						art.setIs_liked(jo1.getString("is_liked"));
						art.setIs_follow(jo1.getString("is_follow"));
						art.setIs_you(jo1.getString("is_you"));
						art.setImage(jo1.getString("contents"));
						jo1 = jo.getJSONObject("com_stat");
						art.setComment_stat(jo1.getInt("com_stat"));
						jo1 = jo.getJSONObject("view_stat");
						art.setView_stat(jo1.getInt("view_stat"));
						jo1 = jo.getJSONObject("like_stat");
						art.setLike_stat(jo1.getInt("like_stat"));
						for (Artwork art1 : so.getUserOther().getArtworks()) {
							if (art1.getString_id().equals(string_id)) {
								found = true;
								art1 = art;
							}
						}
						if (!found)
							so.getUserOther().getArtworks().add(art);

						break;
					case 3:// get artwork comment
						if (jar != null) {
							for (int i = 0; i < jar.length(); i++) {
								jo = jar.getJSONObject(i);
								Comment c = new Comment();
								c.setComment_id(jo.getInt("comment_id"));
								c.setComment_content(jo
										.getString("comment_content"));
								c.setCreatedatetime(jo
										.getString("createdatetime"));
								c.setTime(jo.getString("time"));
								User u = new User();
								u.setUser_id(jo.getInt("user_id"));
								u.setUser_consumer_id(jo
										.getInt("user_consumer_id"));
								u.setFullname(jo.getString("fullname"));
								u.setUser_avatar(jo.getString("avatar"));
								c.setUser(u);
								int artwork_id = jo.getInt("artwork_id");
								Boolean isfound = false;
								for (int j = 0; j < so.getUserOther()
										.getArtworks().size(); j++) {
									if (so.getUserOther().getArtworks().get(j)
											.getId() == artwork_id) {
										if (i == 0)
											so.getUserOther().getArtworks()
													.get(j).getComments()
													.clear();
										so.getUserOther().getArtworks().get(j)
												.getComments().add(c);
									}

								}

							}
						}
						break;
					case 4:// post artwork

						break;
					case 5:// post artwork comment

						break;
					case 6:// like artwork
						String art_id = msg.getData().getString("art_id");
						jo = jo.getJSONObject("total_like");
						int count = jo.getInt("count");
						for (Artwork art1 : so.getDiscoverArtworks()) {
							if (art1.getArtwork_id().equals(art_id))
								art1.setLike_stat(count);
						}
						for (Artwork art1 : so.getUserOther().getArtworks()) {
							if (art1.getArtwork_id().equals(art_id))
								art1.setLike_stat(count);
						}

						break;
					case 7:// report artwork

						break;
					}
					break;
				case so.modul_setting:
					switch (mode) {
					case 1:// edit profile
						so.meta.setErrorDetail(jo.getString("response"));
						break;
					case 2:// get profile
						jo = jar.getJSONObject(0);
						so.getUser()
								.setUser_avatar(jo.getString("user_avatar"));
						so.getUser()
								.setJob_profile(jo.getString("job_profile"));
						so.getUser().setJob_location(
								jo.getString("job_location"));
						so.getUser().setBio(jo.getString("bio"));
						so.getUser().setFullname(jo.getString("fullname"));
						break;
					case 3:// cek token

						break;
					case 4:// get user account
						so.getUser()
								.getAccount()
								.setRegistrationID(
										jo.getString("RegistrationID"));
						so.getUser().getAccount()
								.setBrand1_ID(jo.getString("Brand1_ID"));
						so.getUser().getAccount()
								.setBrand2_ID(jo.getString("Brand2_ID"));
						so.getUser().getAccount()
								.setCityID(jo.getString("CityID"));
						so.getUser().getAccount()
								.setFirstName(jo.getString("FirstName"));
						so.getUser().getAccount()
								.setGender(jo.getString("Gender"));
						so.getUser().getAccount()
								.setLastName(jo.getString("LastName"));
						so.getUser().getAccount()
								.setMobilePhone(jo.getString("MobilePhone"));
						so.getUser().getAccount()
								.setOtherName(jo.getString("OtherName"));
						so.getUser().getAccount()
								.setStateID(jo.getString("StateID"));
						so.getUser().getAccount()
								.setStreetName(jo.getString("StreetName"));
						so.getUser().getAccount()
								.setSubBrand1_ID(jo.getString("SubBrand1_ID"));
						so.getUser().getAccount()
								.setSubBrand2_ID(jo.getString("SubBrand2_ID"));
						break;
					case 5:// update account

						break;
					case 6:// upload ktp

						break;
					}
					break;
				case so.modul_brand:
					switch (mode) {
					case 1:// Brand
						for (int i = 0; i < jar.length(); i++) {
							jo = jar.getJSONObject(i);
							Brand p = new Brand();
							p.setBrandID(jo.getString("BrandID"));
							p.setBrandName(jo.getString("BrandName"));
							p.setBrandCode(jo.getString("BrandCode"));
							p.setMarketID(jo.getString("MarketID"));
							p.setShowOnACS(jo.getString("ShowOnACS"));
							so.getBrands().add(p);
						}
						break;
					case 2:// Sub Brand
						for (int i = 0; i < jar.length(); i++) {
							jo = jar.getJSONObject(i);
							SubBrand c = new SubBrand();
							// String BrandCode = jo.getString("BrandCode");
							c.setSubBrandID(jo.getString("SubBrandID"));
							c.setSubBrandName(jo.getString("SubBrandName"));
							c.setSubBrandCode(jo.getString("SubBrandCode"));
							c.setBrandName(jo.getString("BrandName"));
							c.setBrandCode(jo.getString("BrandCode"));
							// for (Brand p : so.getBrands()) {
							// if (p.getBrandCode().equals(BrandCode))
							// p.getSubBrand().add(c);
							// }
							so.getSubBrands().add(c);
						}
						break;
					case 3:// Province
						for (int i = 0; i < jar.length(); i++) {
							jo = jar.getJSONObject(i);
							Province p = new Province();
							p.setStateID(jo.getString("StateID"));
							p.setStateName(Util.getFirstWordUpper(jo
									.getString("StateName")));
							p.setStateCode(jo.getString("StateCode"));
							p.setShowOnACS(jo.getString("ShowOnACS"));
							so.getProvinces().add(p);
						}
						break;
					case 4:// City
						for (int i = 0; i < jar.length(); i++) {
							jo = jar.getJSONObject(i);
							City c = new City();
							String StateCode = jo.getString("StateCode");
							c.setStateName(jo.getString("StateName"));
							c.setCityID(jo.getInt("CityID"));
							c.setCityCode(jo.getString("CityCode"));
							c.setCityName(Util.getFirstWordUpper(jo
									.getString("CityName")));
							c.setaDBan(jo.getString("ADBan"));
							c.setStateCode(StateCode);
							for (Province p : so.getProvinces()) {
								if (p.getStateCode().equals(StateCode))
									p.getCities().add(c);
							}

						}
						break;
					}
					break;
				default:
					break;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				so.meta.setCode(-3);
				so.meta.setErrorType("parse");
				so.meta.setErrorDetail(e.getMessage() + "\n"
						+ e.getStackTrace().toString());
			}

		} else if (message.contains("\"status\"")) {
			so.meta = new Meta();
			JSONObject jObject;
			try {
				jObject = new JSONObject(message);
				if (jObject.getBoolean("status"))
					so.meta.setCode(200);
				else
					so.meta.setCode(201);
				so.meta.setErrorDetail(jObject.getString("message"));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				so.meta.setCode(-2);
				so.meta.setErrorType("http");
				so.meta.setErrorDetail(message);
			}

		} else {
			so.meta = new Meta();
			so.meta.setCode(-2);
			so.meta.setErrorType("http");
			so.meta.setErrorDetail(message);
		}
	}
}
