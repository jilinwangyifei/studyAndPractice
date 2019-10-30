package Java8.Chapter3;

import java.util.function.Function;

public class Letter {

    public static String addHeader(String text){
        return "From Raoul, Mario and Alan: " + text;
    }
    public static String addFooter(String text){
        return text + " Kind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda", "lambda");
    }

    //3.8.3 函数复合
    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;

        //先处理信封头部 在处理拼写 最后处理信封结尾
        Function<String, String> transformationPipeline =
                addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
    }


}
