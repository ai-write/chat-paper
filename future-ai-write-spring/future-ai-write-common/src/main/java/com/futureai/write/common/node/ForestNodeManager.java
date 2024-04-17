/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package com.futureai.write.common.node;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 森林管理类
 *
 * @author smallchill
 */
public class ForestNodeManager<T extends INode<T>> {

	/**
	 * 森林的所有节点
	 */
	private final ImmutableMap<Long, T> nodeMap;

	/**
	 * 森林的父节点ID
	 */
	private final Map<Long, Object> parentIdMap = Maps.newHashMap();

	public ForestNodeManager(List<T> nodes) {
		nodeMap = Maps.uniqueIndex(nodes, INode::getId);
	}

	/**
	 * 根据节点ID获取一个节点
	 *
	 * @param id 节点ID
	 * @return 对应的节点对象
	 */
	public INode<T> getTreeNodeAt(Long id) {
		if (nodeMap.containsKey(id)) {
			return nodeMap.get(id);
		}
		return null;
	}

	/**
	 * 增加父节点ID
	 *
	 * @param parentId 父节点ID
	 */
	public void addParentId(Long parentId) {
		parentIdMap.put(parentId, StrUtil.EMPTY);
	}

	/**
	 * 获取树的根节点(一个森林对应多颗树)
	 *
	 * @return 树的根节点集合
	 */
	public List<T> getRoot() {
		List<T> roots = new ArrayList<>();
		nodeMap.forEach((key, node) -> {
			if (node.getParentId() == null || parentIdMap.containsKey(node.getId())) {
				roots.add(node);
			}
		});
		return roots;
	}

}
