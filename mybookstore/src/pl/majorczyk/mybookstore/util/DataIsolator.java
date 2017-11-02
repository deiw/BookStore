package pl.majorczyk.mybookstore.util;

import pl.majorczyk.mybookstore.model.Data;
import pl.majorczyk.mybookstore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class DataIsolator {
    public List<Data> getData(List<Order> listOrder){
        List<Data> dataList=new ArrayList<>();
        for(Order order:listOrder){
            String[]multi=order.getTitles().split("[/]");
            for(int i=0;i<multi.length;i++){
                String[]single=multi[i].split("[+]");
                Data data=new Data();
                data.setTitle(single[0]);
                data.setAmount(single[1]);
                data.setPrice(single[2]);
                dataList.add(data);
            }
        }
        return dataList;
    }
}
