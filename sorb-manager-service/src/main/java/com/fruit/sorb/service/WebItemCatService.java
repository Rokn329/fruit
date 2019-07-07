package com.fruit.sorb.service;

import com.fruit.sorb.manager.pojo.ItemCat;
import com.fruit.sorb.manager.pojo.ItemCatData;
import com.fruit.sorb.manager.pojo.ItemCatResultVO;
import com.fruit.sorb.mapper.SysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebItemCatService {

    @Autowired
    private SysMapper<ItemCat> sysMapper;

    public ItemCatResultVO findItemCatList() {
        List<ItemCat> itemCatList = sysMapper.select(null);
        Map<Long, List<ItemCat>> itemCatsMap = new HashMap<Long, List<ItemCat>>();
        for (ItemCat cat : itemCatList) {
            // 先判断这个key是否存在
            if (!itemCatsMap.containsKey(cat.getParentId())) {
                itemCatsMap.put(cat.getParentId(), new ArrayList<ItemCat>());
            }
            itemCatsMap.get(cat.getParentId()).add(cat);
        }

        ItemCatResultVO resultVO = new ItemCatResultVO();

        List<ItemCatData> dataList = new ArrayList<ItemCatData>();
        // 编辑一级目录
        String url = "";
        String name = "";
        for (ItemCat cat1 : itemCatsMap.get(0L)) {
            ItemCatData data1 = new ItemCatData();
            url = "/products/" + cat1.getId() + ".html";
            name = "<a href='" + url + "'>" + cat1.getName() + "</a>";
            data1.setUrl(url);
            data1.setName(name);

            // 二级目录
            List<ItemCatData> dataList1 = new ArrayList<ItemCatData>();
            for (ItemCat cat2 : itemCatsMap.get(cat1.getId())) {
                ItemCatData data2 = new ItemCatData();
                url = "/products/" + cat2.getId() + ".html";
                name = "<a href='" + url + "'>" + cat2.getName() + "</a>";
                data2.setUrl(url);
                data2.setName(name);
                // 三级目录
                List<String> dataList2 = new ArrayList<String>();
                for (ItemCat cat3 : itemCatsMap.get(cat2.getId())) {
                    url = "/products/" + cat3.getId() + ".html|" + cat3.getName();
                    dataList2.add(url);
                }
                data2.setItems(dataList2);
                dataList1.add(data2);
            }
            data1.setItems(dataList1);
            dataList.add(data1);
            if (dataList.size() > 14) {
                break;
            }
        }
        resultVO.setItemCatList(dataList);

        return resultVO;
    }

}
