package com.example.user.n1ne45bookshop2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Book extends java.util.HashMap<String,String>{
    final static String host = "http://172.17.248.213/N1Ne45_Bookshop/Service.svc";
    final static String imageURL = "http://172.17.248.213/N1Ne45_Bookshop/images/images";

    public Book(){}
    public Book(String bookID,String title, String categoryID, String isbn, String author, String price, String discount){
        put("BookID", bookID);
        put("Title",title);
        put("CategoryID",categoryID);
        put("ISBN",isbn);
        put("Author",author);
        put("Price",price);
        put("Discount",discount);
    }
    public static List<Book> listBook() {
        List<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/RetrieveAll");
        try {
            for (int i =0; i<a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("BookID"),
                                  b.getString("Title"),
                                  b.getString("CategoryID"),
                                  b.getString("ISBN"),
                                  b.getString("Author"),
                                  b.getString("Price"),
                                  b.getString("Discount")
                        ));
            }
        } catch (Exception e) {
            Log.e("Book", "JSONArray error");
        }
        return(list);

    }

    public static Book getBook(String id) {
        Book book = null;
        try {
            JSONObject c = JSONParser.getJSONFromUrl(host+"/RetrieveByID/"+id);
            book = new Book(Integer.toString(c.getInt("BookID")),
                            c.getString("Title"),
                            Integer.toString(c.getInt("CategoryID")),
                            c.getString("ISBN"),
                            c.getString("Author"),
                            Double.toString(c.getDouble("Price")),
                            Double.toString(c.getDouble("Discount")));
        } catch (Exception e) {
        }
        return book;
    }

    public static List<Book> listBookByCategory(String cat) {
        List<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/RetrieveByCategory/"+cat);
        try {
            for (int i =0; i<a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("BookID"),
                        b.getString("Title"),
                        b.getString("CategoryID"),
                        b.getString("ISBN"),
                        b.getString("Author"),
                        b.getString("Price"),
                        b.getString("Discount")
                ));
            }
        } catch (Exception e) {
            Log.e("Book", "JSONArray error");
        }
        return(list);
    }

    public static Bitmap getPhoto(String isbn) {
        try {
            URL url = new URL(String.format("%s/%s.jpg",imageURL,isbn));
            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Book.getPhoto()", "Bitmap error");
        }
        return(null);
    }




}
