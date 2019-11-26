package com.fansongsong.service;

import javax.validation.Valid;

import com.fansongsong.entity.Link;
import com.github.pagehelper.PageInfo;

public interface LinkService {

	PageInfo<Link> list(Integer i);

	Object get(Integer id);

	Integer delete(Integer id);

	Integer update(@Valid Link link);

	Integer add(@Valid Link link);

}
