package io.renren.common.utils;

/** 解析 List<Map> 排序
 * arr 数据源
 * index 数据源对应索引号
 */
public class SortUtils {
    public static void quickSort(Double[] arr,int[] index,int low,int high){
        int start=low;//设置可以移动的最小值
        int end=high;//设置可以移动的最大值
        Double key=arr[low];//设置标识
        while(start<end){//整体大循环
            while(start<end&&arr[end]>=key)//先从循环后面的，从后向前，找小于key的
                end--;
            if(arr[end]<=key){
                Double temp=arr[end];
                arr[end]=arr[start];
                arr[start]=temp;

                int tempi = index[end];
                index[end] = index[start];
                index[start]=tempi;
            }
            while(start<end&&arr[start]<=key)//循环前面的，从前往后找大于key的值
                start++;
            if(arr[start]>=key){
                Double temp=arr[start];
                arr[start]=arr[end];
                arr[end]=temp;

                int tempi = index[start];
                index[start] = index[end];
                index[end]=tempi;
            }
        }
        //递归调用
        if(low<start)
            quickSort(arr,index,low,start-1);
        if(end<high)
            quickSort(arr,index,end+1,high);

    }
}
