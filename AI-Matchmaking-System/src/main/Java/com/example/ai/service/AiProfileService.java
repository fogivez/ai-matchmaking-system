package com.example.ai.service;

import com.example.ai.entity.UserProfile;
import com.example.ai.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AiProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * 核心AI功能：传入用户口语化的自我介绍，模拟调用大语言模型(LLM)提取结构化标签
     */
    public UserProfile extractTagsByAi(Long userId, String rawBio) {
        // 1. 查找或创建用户的 Profile
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElse(new UserProfile());
        
        profile.setUserId(userId);
        profile.setRawBio(rawBio);
        profile.setUpdatedAt(LocalDateTime.now());

        // 2. 模拟大模型（如 DeepSeek / OpenAI）的 NLP 文本解析逻辑
        // 在实际生产中，这里会通过 HTTP 客户端调用大模型 API，并传入类似：
        // "请从以下文本中提取兴趣爱好和性格特征，以标准的 JSON 数组返回..."
        String lowerBio = rawBio.toLowerCase();
        
        String mockHobbies = "[]";
        String mockAiTags = "[]";

        // 模拟大模型根据关键词进行特征向量和规则匹配
        if (lowerBio.contains("代码") || lowerBio.contains("程序") || lowerBio.contains("打游戏")) {
            mockHobbies = "[\"计算机\", \"电子游戏\", \"数码产品\"]";
            mockAiTags = "[\"内向\", \"逻辑思维强\", \"技术宅\"]";
        } else if (lowerBio.contains("旅游") || lowerBio.contains("运动") || lowerBio.contains("吉他")) {
            mockHobbies = "[\"户外运动\", \"音乐\", "旅行\"]";
            mockAiTags = "[\"外向\", "开朗\", \"文艺青年\"]";
        } else {
            // 默认兜底标签
            mockHobbies = "[\"阅读\", \"电影\"]";
            mockAiTags = "[\"温和\", \"随性\"]";
        }

        // 3. 将 AI 解析出的结构化 JSON 字符串存入对应的字段
        profile.setHobbies(mockHobbies);
        profile.setAiPersonalityTags(mockAiTags);

        // 4. 持久化落库保存
        return userProfileRepository.save(profile);
    }
}