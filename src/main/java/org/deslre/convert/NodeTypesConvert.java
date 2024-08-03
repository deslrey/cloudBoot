package org.deslre.convert;

import org.deslre.entity.po.GroupMembers;
import org.deslre.entity.po.NodeTypes;
import org.deslre.entity.vo.GroupMembersVO;
import org.deslre.entity.vo.NodeTypesVO;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ClassName: NodeTypesConvert
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-03 14:19
 * Version: 1.0
 */
public interface NodeTypesConvert {

    NodeTypesConvert INSTANCE = Mappers.getMapper(NodeTypesConvert.class);

    NodeTypes convert(NodeTypesVO vo);

    NodeTypesVO convert(NodeTypes entity);

    List<NodeTypesVO> convertList(List<NodeTypes> list);
}
