package com.example.forkjoin;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

@Component
public class RiderOrderForkUtils extends RecursiveTask<List<RiderOrderInfoDTO>> {
    private Date startTime;

    private Date endTime;

    public static final int THRESHOLD = 30;

    public RiderOrderForkUtils(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RiderOrderForkUtils() {
    }

//    private static ShopOrderMapper shopOrderMapper;
//
//    @Autowired
//    public void setShopOrderMapper(ShopOrderMapper shopOrderMapper) {
//        RiderOrderForkUtils.shopOrderMapper = shopOrderMapper;
//    }

    @Override
    protected List<RiderOrderInfoDTO> compute() {
        List<RiderOrderInfoDTO> resultList = new ArrayList<>();
        boolean flag = getTimeDiffer(startTime, endTime) <= THRESHOLD;
        if (flag) {
            //resultList = shopOrderMapper.initRiderOrder(startTime, endTime);
        } else {
            //不满足条件拆分
            Long middle = getTimeDiffer(startTime, endTime) >> 2;
            RiderOrderForkUtils leftTask = new RiderOrderForkUtils(startTime, getEndDate(startTime, middle));
            RiderOrderForkUtils rightTask = new RiderOrderForkUtils(getEndDate(startTime, middle), endTime);
            invokeAll(leftTask,rightTask);
            List<RiderOrderInfoDTO> leftList = leftTask.join();
            List<RiderOrderInfoDTO> rightList = rightTask.join();
            resultList = mergeList(leftList, rightList);
        }
        return resultList;
    }

    public Long getTimeDiffer(Date startTime, Date endTime) {
        LocalDateTime startDateToLocal = startTime.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        LocalDateTime endDateToLocal = endTime.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return Duration.between(startDateToLocal, endDateToLocal).toDays();
    }

    public Date getEndDate(Date startDate, Long day) {
        LocalDateTime dateToLocal = startDate.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return Date.from(dateToLocal.plusDays(day).atZone(ZoneId.systemDefault()).toInstant());
    }

    public List<RiderOrderInfoDTO> mergeList(List<RiderOrderInfoDTO> startList, List<RiderOrderInfoDTO> endList) {
        startList.addAll(endList);
        List<RiderOrderInfoDTO> newList = new ArrayList<>();
        startList.parallelStream().collect(Collectors.groupingBy(o -> o.getRiderId(), Collectors.toList())).forEach(
                (id, trans) -> {
                    trans.stream().reduce((a, b) -> new RiderOrderInfoDTO(a.getRiderId(), b.getOrderCount() + a.getOrderCount())).ifPresent(newList::add);
                }
        );
        return newList;

    }


}
