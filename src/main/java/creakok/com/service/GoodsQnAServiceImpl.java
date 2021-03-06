package creakok.com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import creakok.com.domain.Goods_QnA;
import creakok.com.domain.Goods_Review;
import creakok.com.mapper.GoodsQnAMapper;
import creakok.com.vo.Goods_QnAVo;
import creakok.com.vo.Goods_ReviewVo;
import lombok.extern.log4j.Log4j;

@Log4j
@Service("GoodsQnAService")
public class GoodsQnAServiceImpl implements GoodsQnAService {
	@Autowired
	GoodsQnAMapper goods_qnaMapper;
	
	@Override
	public Goods_QnAVo selectPerPageQnA(int qna_cp, int qna_ps, long goods_index){
		//log.info("????????????????????????????????? qna_cp: "+qna_cp);
		//log.info("????????????????????????????????? qna_ps: "+qna_ps);
		//log.info("????????????????????????????????? goods_index: "+goods_index);
		long qna_totalCount = selectGoodsQnACountByGoodsIndex(goods_index);
		log.info("????????????????????????????????? qna_totalCount: "+qna_totalCount);
		Goods_QnAVo goods_qna_vo = new Goods_QnAVo(qna_cp, qna_totalCount, qna_ps, goods_index, null);
		List<Goods_QnA> qna_list = goods_qnaMapper.selectPerPageQnA(goods_qna_vo);

		//문의 글 번호
		long qna_list_number;
		for(int i=0; i<qna_list.size(); i++) {
			qna_list_number = qna_totalCount - ((qna_cp-1)*qna_ps)-i;
			qna_list.get(i).setQna_list_number(qna_list_number);
		}
		
		Goods_QnAVo goods_qna_vo2 = new Goods_QnAVo(qna_cp, qna_totalCount, qna_ps, goods_index, qna_list);
		goods_qna_vo2.setTotalPageCount(goods_qna_vo2.getTotalPageCount());
		
		
		return goods_qna_vo2;
	}
	@Override
	public long selectGoodsQnACountByGoodsIndex(long goods_index) {
		return goods_qnaMapper.selectGoodsQnACountByGoodsIndex(goods_index);
	}
	@Override
	public Goods_QnA selectOneQnA( long goods_qna_index) {
		return goods_qnaMapper.selectOneQnA(goods_qna_index);
	}
	@Override
	public void updateOneAnswer(Goods_QnA goods_qna) {
		goods_qnaMapper.updateOneAnswer(goods_qna);
	}
	@Override
	public void deleteOneAnswer( long goods_qna_index) {
		goods_qnaMapper.deleteOneAnswer(goods_qna_index);
	}
	@Override
	public void deleteOneQnA(long goods_qna_index) {
		goods_qnaMapper.deleteOneQnA(goods_qna_index);
	}
	@Override
	public void updateOneQnA(Goods_QnA goods_qna) {
		goods_qnaMapper.updateOneQnA(goods_qna);
	}
	@Override
	public void insertOneQnA(Goods_QnA goods_qna) {
		goods_qnaMapper.insertOneQnA(goods_qna);
	}
}