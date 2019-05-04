# 游戏消极语言分析任务路线

## 构建训练集
+ 标注训练集（data_phase1.txt）
    + 筛选出 100 - 200 条具有代表性的语料
    + 对语料进行正确标注
+ 文本纠错（data_phase2.txt）
    + 有些玩家会使用错别字避开系统屏蔽词
    + 使用 pycorrector 进行文本纠错
+ 文本分词（data_phase3.txt）
    + 使用 HanLP 分词
    
## 训练词向量
+ 准备训练词向量所需语料数据（segForVec.txt）
    + 取所有数据
    + 文本纠错
    + 分词，不保留词性，用空格分割
+ 使用 HanLP - word2vec 训练词向量（msr_vectors.txt）
    + [API训练](https://github.com/hankcs/HanLP/wiki/word2vec#api%E8%AE%AD%E7%BB%83)
+ 计算文档向量，用于短文本的相似度计算
    + 准备数据 （corrected_train_data.txt）
    + 文档相似度计算
    + 词汇相关度计算

