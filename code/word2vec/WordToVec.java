package sentiment;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.Vector;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.*;
import java.util.List;
import java.util.Map;

public class WordToVec {
    public static void main(String[] args) throws IOException{

        //加载词向量
        DocVectorModel docVectorModel = new DocVectorModel(new WordVectorModel("msr_vectors.txt"));
        WordVectorModel wordVectorModel = new WordVectorModel("msr_vectors.txt");


        FileReader fr = new FileReader("train_data.txt");
        LineNumberReader reader = new LineNumberReader(fr);
        reader.skip(Long.MAX_VALUE);
        int lines = reader.getLineNumber();
        System.out.println(lines);
        reader.close();
        fr = new FileReader("train_data.txt");
        BufferedReader br = new BufferedReader(fr);
        String[] documents = new String[80000];

        for(int i = 0  ; i < lines; i++){
            String str = br.readLine();
            String[] block = str.split(" ");
            docVectorModel.addDocument(i,block[1]);
            documents[i] = block[1];
        }

        //和「关键词」相关度最高的语料
        System.out.println("============演员=============");
        List<Map.Entry<Integer, Float>> entryList = docVectorModel.nearest("演员");
        for (Map.Entry<Integer, Float> entry : entryList)
        {
            System.out.printf("%d %s %.2f\n", entry.getKey(), documents[entry.getKey()], entry.getValue());
        }

        System.out.println("============皮肤=============");
        entryList = docVectorModel.nearest("皮肤");
        for (Map.Entry<Integer, Float> entry : entryList)
        {
            System.out.printf("%d %s %.2f\n", entry.getKey(), documents[entry.getKey()], entry.getValue());
        }

        //文档相似度计算
        String str1 = "送我庄周皮肤好不好，张忠良。";
        String str2 = "你自己坑怪我们送";
        System.out.println(str1+" - "+str2+": "+docVectorModel.similarity(str1, str2));

        //
        str1 = "送我庄周皮肤好不好，张忠良。";
        str2 = "吕布你皮肤送给我吧";
        System.out.println(str1+" - "+str2+": "+docVectorModel.similarity(str1, str2));

        str1 = "你们慢慢打我送了。";
        str2 = "我就送对面十个人头。";
        System.out.println(str1+" - "+str2+": "+docVectorModel.similarity(str1, str2));

        str1 = "打野都说我是演员了";
        str2 = "我就送对面十个人头。";
        System.out.println(str1+" - "+str2+": "+docVectorModel.similarity(str1, str2));

        //和「演员」相关度最高的词
        //System.out.println(wordVectorModel.nearest("演员"));

    }


}
