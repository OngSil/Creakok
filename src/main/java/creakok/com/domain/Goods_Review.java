package creakok.com.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods_Review {
	private long goods_review_index;
	private String member_name;
	private long goods_index;
	private Date goods_review_date;
	private long goods_review_rating;
	private String goods_review_pic;
	private String goods_review_subject;
	private String goods_review_content;
	private String member_profile_pic;
	private long goods_review_view;
	private long list_number; //게시판 글 번호
}