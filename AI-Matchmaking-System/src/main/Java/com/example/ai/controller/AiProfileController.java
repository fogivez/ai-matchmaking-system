package com.example.ai.controller;

import com.example.ai.entity.UserProfile;
import com.example.ai.service.AiProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
// 开启跨域支持，允许 Vue 3 前端进行无缝接口对接
@CrossOrigin(origins = "*")
public class AiProfileController {

    @Autowired
    private AiProfileService aiProfileService;

    /**
     * 接口：POST /api/ai/extract-tags
     * 作用：接收用户输入的口语化自我介绍，返回由 AI 解析并落库的结构化标签结果
     */
    @PostMapping("/extract-tags")
    public ResponseEntity<Map<String, Object>> extractProfileTags(
            @RequestParam Long userId,
            @RequestBody Map<String, String> requestBody) {
        
        String rawBio = requestBody.get("rawBio");
        Map<String, Object> response = new HashMap<>();

        // 参数校验：符合严谨的工程安全规约
        if (rawBio == null || rawBio.trim().isEmpty()) {
            response.put("code", 400);
            response.put("message", "自我介绍内容不能为空");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // 调用 Service 层核心 AI 逻辑
            UserProfile updatedProfile = aiProfileService.extractTagsByAi(userId, rawBio);
            
            // 组装标准的 JSON 响应结构
            response.put("code", 200);
            response.put("message", "AI 标签提取成功");
            response.put("data", updatedProfile);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("code", 500);
            response.put("message", "AI 服务内部错误: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}