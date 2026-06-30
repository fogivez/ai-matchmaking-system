<template>
  <div class="min-h-screen bg-gray-100 p-6 flex flex-col items-center">
    <header class="w-full max-w-4xl bg-white shadow-md rounded-lg p-4 mb-6 flex justify-between items-center">
      <h1 class="text-xl font-bold text-blue-600">💖 AI 智能婚恋交友系统</h1>
      <span class="bg-green-100 text-green-800 text-xs font-semibold px-2.5 py-0.5 rounded">AI 引擎已就绪</span>
    </header>

    <main class="w-full max-w-4xl grid grid-cols-1 md:grid-cols-2 gap-6">
      
      <section class="bg-white p-6 rounded-lg shadow-md flex flex-col">
        <h2 class="text-lg font-bold text-gray-800 mb-3 flex items-center">
          <span class="mr-2">📝</span> AI 智能画像提取
        </h2>
        <p class="text-sm text-gray-500 mb-4">输入一段口语化的自我介绍，AI将自动为您分析性格并打上特征标签。</p>
        
        <textarea 
          v-model="rawBio" 
          placeholder="例如：我是一名软件工程专业的学生，平时喜欢写代码和打游戏，性格有点内向，但是熟了之后很开朗..." 
          class="w-full h-32 p-3 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none text-sm mb-4"
        ></textarea>
        
        <button 
          @click="extractTags" 
          :disabled="loading"
          class="w-full bg-blue-600 hover:bg-blue-700 text-white font-medium py-2 px-4 rounded-lg transition disabled:bg-gray-400"
        >
          {{ loading ? 'AI 正在深度解析中...' : '开始 AI 智能提取' }}
        </button>

        <div v-if="profile" class="mt-6 p-4 bg-blue-50 rounded-lg border border-blue-100 animate-fade-in">
          <h3 class="text-sm font-bold text-blue-800 mb-2">✨ AI 提取画像结果：</h3>
          <div class="mb-3">
            <span class="text-xs text-gray-600 block mb-1">兴趣爱好：</span>
            <div class="flex flex-wrap gap-2">
              <span v-for="tag in profile.hobbies" :key="tag" class="bg-blue-100 text-blue-800 text-xs px-2 py-1 rounded-full"># {{ tag }}</span>
            </div>
          </div>
          <div>
            <span class="text-xs text-gray-600 block mb-1">AI性格标签：</span>
            <div class="flex flex-wrap gap-2">
              <span v-for="tag in profile.aiPersonalityTags" :key="tag" class="bg-purple-100 text-purple-800 text-xs px-2 py-1 rounded-full"># {{ tag }}</span>
            </div>
          </div>
        </div>
      </section>

      <section class="bg-white p-6 rounded-lg shadow-md flex flex-col justify-between">
        <div>
          <h2 class="text-lg font-bold text-gray-800 mb-3 flex items-center">
            <span class="mr-2">💬</span> 互动与 AI 破冰助手
          </h2>
          <p class="text-sm text-gray-500 mb-4">系统检测到您与当前匹配对象有共同爱好，AI已为您生成破冰开场白：</p>
          
          <div class="space-y-3">
            <div 
              v-for="(icebreaker, index) in icebreakers" 
              :key="index"
              @click="selectIcebreaker(icebreaker)"
              class="p-3 bg-gray-50 hover:bg-green-50 border border-gray-200 hover:border-green-300 rounded-lg cursor-pointer transition text-sm text-gray-700"
            >
              <span class="text-green-600 font-bold mr-1">💡 话术 {{ index + 1 }}:</span> "{{ icebreaker }}"
            </div>
          </div>
        </div>

        <div class="mt-6">
          <div class="flex gap-2">
            <input 
              v-model="chatInput" 
              type="text" 
              placeholder="点击上方话术直接填充，或在此输入..." 
              class="flex-1 p-2 border rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-green-500"
            />
            <button class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-lg text-sm font-medium">
              发送
            </button>
          </div>
        </div>
      </section>

    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rawBio: '',
      loading: false,
      profile: null,
      chatInput: '',
      icebreakers: [
        "发现你也很喜欢看悬疑电影，最近那部反转剧你看了吗？",
        "好巧啊，我们都是软件工程专业的，平时经常要熬夜改Bug吗？",
        "看到你的标签里有独立游戏，最近有通关什么好玩的游戏推荐吗？"
      ]
    }
  },
  methods: {
    extractTags() {
      if (!this.rawBio.trim()) return;
      this.loading = true;
      
      // 模拟前端发起 Ajax 请求对接后端 API 的过程
      setTimeout(() => {
        this.loading = false;
        // 模拟后端根据输入关键词返回的真实结构化 JSON
        if (this.rawBio.includes('代码') || this.rawBio.includes('游戏')) {
          this.profile = {
            hobbies: ['计算机', '电子游戏', '数码产品'],
            aiPersonalityTags: ['内向', '逻辑思维强', '技术宅']
          };
        } else {
          this.profile = {
            hobbies: ['阅读', '电影', '旅行'],
            aiPersonalityTags: ['温和', '随性', '文艺青年']
          };
        }
      }, 1200);
    },
    selectIcebreaker(text) {
      this.chatInput = text;
    }
  }
}
</script>
