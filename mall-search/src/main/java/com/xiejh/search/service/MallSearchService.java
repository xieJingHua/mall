package com.xiejh.search.service;

import com.xiejh.search.vo.SearchParam;
import com.xiejh.search.vo.SearchResult;

/**
 * @author xiejh
 * @Date 2020/11/9 20:47
 **/
public interface MallSearchService {
    SearchResult search(SearchParam param);
}
