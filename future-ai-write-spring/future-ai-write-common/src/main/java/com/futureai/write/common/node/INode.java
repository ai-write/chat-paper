package com.futureai.write.common.node;

import java.io.Serializable;
import java.util.List;

public interface INode<T> extends Serializable {

	/**
	 * 主键
	 *
	 * @return Long
	 */
	Long getId();

	/**
	 * 父主键
	 *
	 * @return Long
	 */
	Long getParentId();

	/**
	 * 子孙节点
	 *
	 * @return List<T>
	 */
	List<T> getChildren();

	/**
	 * 是否有子孙节点
	 *
	 * @return Boolean
	 */
	default Boolean getHasChildren() {
		return false;
	}

}
