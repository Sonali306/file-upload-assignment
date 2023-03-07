package com.dbs.assignment.Service;

import com.dbs.assignment.Exception.MarginNotFoundException;
import com.dbs.assignment.Helper.CSVHelper;
import com.dbs.assignment.Model.FindMarginPayLoad;
import com.dbs.assignment.Model.Margin;
import com.dbs.assignment.Repository.MarginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarginService {

    @Autowired
    MarginRepository marginRepository;

    public void save(MultipartFile file) {
        try {
            List<Margin> margins = CSVHelper.csvToMargin(file.getInputStream());

            marginRepository.deleteAll();
            marginRepository.saveAll(margins);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store csv data: "+e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        Iterable<Margin> iterable = marginRepository.findAll();
        List<Margin> margins = new ArrayList<>();
        iterable.forEach(margin->margins.add(margin));
        ByteArrayInputStream result = CSVHelper.marginsToCSV(margins);
        return result;
    }

    public Margin findMarginByPayLoad (FindMarginPayLoad payLoad) {
        List<Margin> margins = marginRepository.findMarginByPayLoad(payLoad.getInstruction(), payLoad.getBaseCcy(), payLoad.getTermCcy(),payLoad.getTradeTier(),payLoad.getAmount());
        if(margins.size()==0) {
            throw new MarginNotFoundException("Margin Not found");
        }
        return margins.get(0);
    }
}
