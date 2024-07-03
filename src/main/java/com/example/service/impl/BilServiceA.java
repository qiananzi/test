package com.example.service.impl;

import com.example.mapper.BilMapper;
import com.example.pojo.Bil.Bil;
import com.example.pojo.Bil.BilRes;
import com.example.pojo.Bil.BilReturn;
import com.example.service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.*;

@Service
public class BilServiceA implements BilService {

    @Autowired
    private BilMapper bilMapper;

    @Override
    public List<BilReturn> listBil() {
        List<Bil> bilList = bilMapper.listBil();
        List<BilRes> bilResList = bilMapper.listBilRes();
        List<BilReturn> bilreturnList = new LinkedList<>();
        bilList.forEach(bil -> {
            List<List<Long>> dateList = new ArrayList<>();
            String state = bil.getStatus();
            switch (state) {
                case "1" -> bil.setStatus("空闲");
                case "2" -> bil.setStatus("忙碌");
                case "3" -> bil.setStatus("维修中");
                case null, default -> bil.setStatus("暂未开放");
            }
            bilResList.forEach(bilRes -> {
                if (Objects.equals(bilRes.getTableID(), bil.getTableID())) {
                    List<Long> dateList_item = new ArrayList<>();
                    dateList_item.add(bilRes.getStartTime().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
                    dateList_item.add(bilRes.getEndTime().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
                    dateList.add(dateList_item);
                }
            });
            dateList.sort(Comparator.comparing(List::getFirst));

            // 对dateList中的时间区间进行原地合并
            int currentIndex = 0; // 当前正在检查的区间索引
            while (currentIndex < dateList.size() - 1) {
                List<Long> currentInterval = dateList.get(currentIndex);
                List<Long> nextInterval = dateList.get(currentIndex + 1);

                // 检查当前区间和下一个区间是否可以合并
                if (isOverlapOrAdjacent(currentInterval.get(0), currentInterval.get(1), nextInterval.get(0), nextInterval.get(1))) {
                    // 合并区间
                    List<Long> mergedInterval = mergeIntervals(
                            currentInterval.get(0), currentInterval.get(1),
                            nextInterval.get(0), nextInterval.get(1)
                    );
                    dateList.set(currentIndex, mergedInterval); // 更新当前区间为合并后的区间
                    dateList.remove(currentIndex + 1); // 移除已合并的下一个区间
                } else {
                    currentIndex++; // 没有重叠，移动到下一个区间
                }
            }
            bilreturnList.add(new BilReturn(bil, dateList));
        });
        return bilreturnList;
    }

    // 辅助方法：判断两个时间区间是否重叠或相邻
    private boolean isOverlapOrAdjacent(Long start1, Long end1, Long start2, Long end2) {
        // 时间区间重叠条件：end1 >= start2 且 start1 <= end2
        // 或者说是：结束时间 >= 对方的开始时间 且 开始时间 <= 对方的结束时间
        return start1 <= end2 && end1 >= start2;
    }

    // 辅助方法：合并两个时间区间
    private List<Long> mergeIntervals(Long start1, Long end1, Long start2, Long end2) {
        // 合并区间取两个区间的最小开始时间和最大结束时间
        return Arrays.asList(
                start1 < start2 ? start1 : start2,
                end1 > end2 ? end1 : end2
        );
    }
}

