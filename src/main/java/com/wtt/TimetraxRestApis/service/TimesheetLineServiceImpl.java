package com.wtt.TimetraxRestApis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.dto.TimesheetLineHourResponseDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetResponseLineDTO;
import com.wtt.TimetraxRestApis.repository.ProjectTaskRepository;
import com.wtt.TimetraxRestApis.repository.TimesheetLineRepository;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesheetLineServiceImpl implements TimesheetLineService {

	@Autowired
    private  TimesheetLineRepository lineRepository;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    ProjectTaskRepository projectTaskRepository;

//    public TimesheetLineServiceImpl(TimesheetLineRepository lineRepository, ModelMapper modelMapper) {
//        this.lineRepository = lineRepository;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public List<TimesheetResponseLineDTO> getAllLinesByTimesheetId(Integer timesheetId) {
    	// strict model mapping
    	modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
    	
    	List<TimesheetResponseLineDTO> dtos= lineRepository.findByTimesheet_TimesheetId(timesheetId).stream().map(line -> {
            TimesheetResponseLineDTO dto = modelMapper.map(line, TimesheetResponseLineDTO.class);

            dto.setTimesheetId(line.getTimesheet().getTimesheetId());
            dto.setTaskId(line.getTask().getTaskId());
            //dto.setTaskName(line.getTask() != null ? line.getTask().getTaskName() : null);
            dto.setTaskName(line.getTask().getTaskName());
            dto.setProjectId(line.getProject().getProjectId());
            dto.setProjectName(line.getProject().getProjectName());


            List<TimesheetLineHourResponseDTO> hourDTOs = line.getHours().stream()
                .map(hour -> modelMapper.map(hour, TimesheetLineHourResponseDTO.class))
                .collect(Collectors.toList());

            dto.setHours(hourDTOs);
            return dto;
        }).collect(Collectors.toList());
    	
    	return dtos;
    }
}

