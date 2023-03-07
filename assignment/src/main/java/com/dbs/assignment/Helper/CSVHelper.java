package com.dbs.assignment.Helper;

import com.dbs.assignment.Model.Margin;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CSVHelper {


    public static String TYPE = "text/csv";
    static String[] HEADERs = { "For Comment","Instruction","Base Ccy","Term Ccy","From Amount","To Amount","Amt Ccy","Bid Operator","Bid Modifier","Ask Operator","Ask Modifier","Remarks" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Margin> csvToMargin(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){

            List<Margin> margins = new ArrayList<Margin>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Margin margin = new Margin(
                        csvRecord.get(1),
                        csvRecord.get(2),
                        csvRecord.get(3),
                        csvRecord.get(4),
                        csvRecord.get(5),
                        csvRecord.get(6),
                        csvRecord.get(7),
                        csvRecord.get(8).charAt(0),
                        csvRecord.get(9),
                        csvRecord.get(10).charAt(0),
                        csvRecord.get(11),
                        csvRecord.get(12)
                );
                //Validation
                MarginValidator.validateMargin(margin);

                margins.add(margin);
            }
            return margins;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream marginsToCSV(List<Margin> margins) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            List<String> headers = Arrays.asList("For Comment","Instruction","Base Ccy","Term Ccy","From Amount","To Amount","Amt Ccy","Bid Operator","Bid Modifier","Ask Operator","Ask Modifier","Remarks");
            csvPrinter.printRecord(headers);
            for (Margin margin : margins) {

                List<String> data = Arrays.asList(
                        null,
                        margin.getInstruction(),
                        margin.getBaseCcy(),
                        margin.getTermCcy(),
                        margin.getTradeTier(),
                        margin.getFromAmount(),
                        margin.getToAmt(),
                        margin.getAmountCcy(),
                        String.valueOf(margin.getBidOperator()),
                        margin.getBidModifier(),
                        String.valueOf(margin.getAskOperator()),
                        margin.getAskModifier(),
                        margin.getRemarks()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
