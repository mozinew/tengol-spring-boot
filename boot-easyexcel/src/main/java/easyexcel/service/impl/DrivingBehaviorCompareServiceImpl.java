package easyexcel.service.impl;

import easyexcel.dto.ColumnData;
import easyexcel.dto.ScoreDTO;
import easyexcel.service.DrivingBehaviorCompareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DrivingBehaviorCompareServiceImpl
 *
 * @author dongrui
 * @date 2020/7/29 11:22
 */
@Slf4j
@Service
public class DrivingBehaviorCompareServiceImpl implements DrivingBehaviorCompareService {
    @Override
    public List<ScoreDTO> getData() {
        List<ScoreDTO> scores = new ArrayList<>();

        scores.add(ScoreDTO.builder().id(1).name("语文").score(90).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(2).name("数学").score(100).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(3).name("英语").score(80).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(4).name("生物").score(80).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(5).name("化学").score(80).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(6).name("物理").score(70).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(7).name("政治").score(90).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(8).name("体育").score(80).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(9).name("绘画").score(60).time(new Date()).build());
        scores.add(ScoreDTO.builder().id(10).name("音乐").score(60).time(new Date()).build());

        return scores;
    }

    /**
     * 获取列格式的业务数据
     *
     * @return 列格式的数据
     */
    @Override
    public List<ColumnData> getDataByColumn() {
        List<ScoreDTO> data = getData();

        List<ColumnData> columnData = new ArrayList<>();
        ColumnData c1 = new ColumnData("课程名称");
        ColumnData c2 = new ColumnData("考试分数");
        ColumnData c3 = new ColumnData("考试时间");
        for (int i = 0; i < 4; i++) {
            ScoreDTO s = data.get(i);
            if (i == 0) {
                c1.setValue1(s.getName());
                c2.setValue1(String.valueOf(s.getScore()));
                c3.setValue1(dateFormatter(s.getTime()));
            }
            if (i == 1) {
                c1.setValue2(s.getName());
                c2.setValue2(String.valueOf(s.getScore()));
                c3.setValue2(dateFormatter(s.getTime()));
            }
            if (i == 2) {
                c1.setValue3(s.getName());
                c2.setValue3(String.valueOf(s.getScore()));
                c3.setValue3(dateFormatter(s.getTime()));
            }
            if (i == 3) {
                c1.setValue4(s.getName());
                c2.setValue4(String.valueOf(s.getScore()));
                c3.setValue4(dateFormatter(s.getTime()));
            }
        }
        columnData.add(c1);
        columnData.add(c2);
        columnData.add(c3);

        return columnData;
    }

    private String dateFormatter(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        return sdf.format(date);
    }
}
