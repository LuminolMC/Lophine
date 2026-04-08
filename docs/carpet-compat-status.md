### Carpet 原版

| 规则 | 状态 | 说明 |
| --- | --- | --- |
| `language` | 已映射 | 转发到 `lophine.function.language.lang` |
| `commandTick` | 已映射 | 转发到现有的 tick 指令补丁 |
| `creativeNoClip` | 已映射 | 转发到 Lophine 的创造飞行穿墙实现 |
| `ctrlQCraftingFix` | 结构等价 | 当前的合成与切石机菜单已经自带修复后的 Ctrl+Q 结果槽丢弃行为 |
| `placementRotationFix` | 已移植 | 放置朝向判断可使用玩家身体朝向而不是头部旋转 |
| `tntDoNotUpdate` | 已移植 | 新放置的 TNT 不会再因放置时更新而自动点燃 |
| `explosionNoBlockDamage` | 已移植 | 爆炸仍会伤害实体，但不会破坏方块 |
| `interactionUpdates` | 已移植 | 玩家交互和破坏方块时可以在不触发邻居更新与形状更新的情况下运行 |
| `xpNoCooldown` | 已移植 | 经验球可以在同一 tick 内被连续吸收，不再有拾取冷却 |
| `viewDistance` | 已移植 | 专用服务器启动视距会被兼容配置覆盖 |
| `fastRedstoneDust` | 已移植 | 规则启用时，红石粉更新会统一走 Alternate Current 快速更新后端 |
| `lagFreeSpawning` | 已移植 | 自然生成会使用轻量碰撞检查与预构造实体路径 |
| `defaultLoggers` | 已移植 | 按 Carpet 默认订阅语义为玩家启用 HUD logger，当前支持 `tps`、`mobcaps` |
| `commandPlayer` | 已映射 | 当前由同一套 `/bot` 假人能力承接 |
| `hopperCounters` | 已映射 | 转发到 Lophine 的羊毛漏斗计数器 |

### Carpet TIS Addition

| 规则 | 状态 | 说明 |
| --- | --- | --- |
| `yeetUpdateSuppressionCrash` | 已映射 | 转发到同一套 Lophine 崩溃修复逻辑 |
| `dustTrapdoorReintroduced` | 已映射 | 转发到 `lophine.experiment.redstone.redstone-ignore-upwards-update` |
| `shulkerBoxCCEReintroduced` | 已映射 | 转发到 `lophine.experiment.redstone.cce-update-suppression` |
| `instantBlockUpdaterReintroduced` | 已映射 | 转发到 `lophine.experiment.redstone.instant-block-updater` |
| `optimizedDragonRespawn` | 已映射 | 转发到 Luminol 的末影龙重生优化 |
| `antiSpamDisabled` | 已移植 | 在 `ServerGamePacketListenerImpl` 中关闭聊天和创造丢物防刷屏限制 |
| `blockPlacementIgnoreEntity` | 已移植 | 创造模式放置方块时忽略实体碰撞检查 |
| `creativeOpenContainerForcibly` | 已移植 | 创造玩家可以强制打开被阻挡的箱子、末影箱和潜影盒 |
| `observerNoDetection` | 已移植 | 观察者的检测触发会在发出脉冲前被取消 |
| `creativeNoItemCooldown` | 已移植 | 创造玩家不会应用物品冷却 |
| `totallyNoBlockUpdate` | 已移植 | 邻居更新和形状更新会在 `NeighborUpdater` 中被统一短路 |
| `tiscmNetworkProtocol` | 已移植 | 原生 `tiscm:network/v1` 握手与数据包协商已经通过专用 Leaves 协议接入 |
| `optimizedTNTHighPriority` | 结构等价 | 当前 `ServerExplosion` 路径已经运行在优化过的服务端爆炸实现上 |
| `tntIgnoreRedstoneSignal` | 已移植 | TNT 在自动点燃判断时忽略红石信号 |
| `tntDupingFix` | 已移植 | 活塞复制 TNT 的路径现在由 `PistonBaseBlock` 中的兼容规则直接控制 |
| `clientSettingsLostOnRespawnFix` | 已移植 | 玩家上一次的客户端设置会在 `ServerPlayer.restoreFrom` 中恢复 |
| `entityInstantDeathRemoval` | 已移植 | 已死亡生物不会再保留原版的 20gt 延迟，而是立即移除 |
| `farmlandTrampledDisabled` | 已移植 | 耕地不会再因实体踩踏而变回泥土 |
| `yeetOutOfOrderChatKick` | 已移植 | 乱序的签名聊天不会再破坏安全聊天链 |
| `tickCommandPermission` | 已移植 | `/tick` 的权限等级可通过兼容配置调整 |
| `tickFreezeCommandToggleable` | 已移植 | 已冻结时再次执行 `/tick freeze` 会切换回运行状态 |
| `syncServerMsptMetricsData` | 已移植 | 实时 MSPT 样本会通过原生 TISCM 协议通道广播 |
| `microTiming` | 结构等价 | 当前 Folia 的区块级性能分析与计时钩子已经提供对应的服务端观测能力 |
| `optimizedFastEntityMovement` | 结构等价 | Moonrise/Paper 的快速实体移动碰撞优化已经是基础运行时的一部分 |
| `optimizedHardHitBoxEntityCollision` | 结构等价 | Moonrise/Paper 的硬碰撞箱实体碰撞优化已经是基础运行时的一部分 |
| `tntFuseDuration` | 已移植 | 可配置的 TNT 引信时长已接入 NMS TNT 逻辑 |
| `fakePlayerTicksLikeRealPlayer` | 已映射 | 转发到 Leaves 的假人网络阶段 tick 行为 |
| `hopperCountersUnlimitedSpeed` | 已映射 | 转发到不限速计数器模式 |

### Carpet Org Addition

| 规则 | 状态 | 说明 |
| --- | --- | --- |
| `hopperNoItemCost` | 已移植 | 漏斗上方放置羊毛时，可在传输后恢复被推出的物品堆，实现零消耗供给线 |
| `simpleInGameCalculator` | 已移植 | 以 `=` 开头的聊天内容会作为简单计算表达式私聊回复 |

### Carpet AMS Addition

| 规则 | 状态 | 说明 |
| --- | --- | --- |
| `amsUpdateSuppressionCrashFix` | 已映射 | 转发到 Lophine 现有的更新抑制崩溃修复 |
| `creativeOneHitKill` | 已移植 | 创造玩家可通过 `Player.attack` 瞬间击杀附近符合条件的目标 |
| `bambooModelNoOffset` | 已移植 | 竹子和竹笋始终返回零模型偏移 |
| `carpetAlwaysSetDefault` | 结构等价 | 兼容配置默认值已经会在 `ConfigsInstance.preLoadConfig` 中被写入 |
| `powerfulExpMending` | 已移植 | 经验拾取会修复玩家整个背包内所有受损的经验修补物品 |
| `sensibleEnderman` | 已移植 | 末影人仅会在规则开启时拾取南瓜和西瓜 |
| `shulkerGolem` | 已移植 | 潜影盒上方放置雕刻南瓜时可以召唤潜影贝 |
| `preventEndSpikeRespawn` | 已移植 | 末影龙重生时会跳过黑曜石柱重建和柱顶水晶重建 |
| `betterCraftableBoneBlock` | 已移植 | 向配方管理器注入 AMS 的替代骨块配方 |
| `betterCraftableDispenser` | 已移植 | 向配方管理器注入 AMS 的替代发射器配方 |
| `fakePlayerDefaultSurvivalMode` | 已移植 | 新创建的假人可以被强制设为生存模式，而不是沿用服务器默认游戏模式 |
| `fakePlayerInteractLikeClient` | 已移植 | 假人与盔甲架交互时会先按真实客户端方式回退，再进入普通实体交互 |
| `fakePlayerAutoReplenishmentFormShulkerBox` | 已移植 | 假人自动补货现在可以从背包里的潜影盒中抽取匹配物品 |

### Lophine 扩展承接

| 规则 | 状态 | 说明                               |
| --- | --- |----------------------------------|
| `commandBot` | 已映射 | 启用 Lophine `/bot`                |
| `fakePlayerResident` | 已映射 | 转发到 Lophine 的假人常驻模式              |
| `openFakePlayerInventory` | 已映射 | 转发到 Lophine 的假人背包打开功能            |
| `fakePlayerAutoReplaceTool` | 已映射 | 为现有自动换工具逻辑增加了新开关                 |
| `fakePlayerAutoReplenishment` | 已映射 | 为现有自动补货逻辑增加了新开关                  |
| `fakePlayerReloadAction` | 已映射 | 为假人动作持久化增加了新开关                   |
| `fakePlayerAutoFish` | 已移植 | 手持鱼竿的假人会自动抛竿和收杆，除非显式执行 `fish` 动作 |
