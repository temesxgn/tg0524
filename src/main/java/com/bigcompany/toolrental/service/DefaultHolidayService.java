package com.bigcompany.toolrental.service;

import com.bigcompany.toolrental.mapper.HolidayMapper;
import com.bigcompany.toolrental.model.Holiday;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultHolidayService implements HolidayService {

    private final HolidayMapper holidayMapper;
    private final SqlSessionFactory sqlSessionFactory;

    @Transactional
    public void insertHolidays(List<Holiday> holidays) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            HolidayMapper mapper = sqlSession.getMapper(HolidayMapper.class);
            for (Holiday holiday : holidays) {
                mapper.insertHoliday(holiday);
            }
            sqlSession.commit();
        }
    }

    @Transactional
    public void deleteBeforeYear(int year) {
        holidayMapper.deleteBeforeYear(year);
    }

    @Transactional(readOnly = true)
    public List<Holiday> findHolidaysBetween(LocalDate startDate, LocalDate endDate) {
        return holidayMapper.findHolidaysBetween(startDate, endDate);
    }

}
