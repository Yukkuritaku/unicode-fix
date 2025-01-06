English version of Readme is [here](https://github.com/Yukkuritaku/unicode-fix/blob/1.12.2/README.MD).

## Unicode Fix

### 概要

特定のModで文字化けが起きてしまう問題や、1.12.2以下の環境で「Unicodeフォントを使用する」をオフにしていても、
強制的にUnicodeフォントを使用してしまうバグを直すModです。

対応しているバージョン: 1.12.2、1.8.9、1.7.10

1.13.x以上の環境では修正されているので作っていません。

<details><summary>実装してるもの </summary>

<details open><summary>マイクラの修正関連</summary>

* `Unicodeフォントの強制/Unicodeフォントを強制する`が機能しないバグを修正

* `Unicodeフォントの強制/Unicodeフォントを強制する: オフ`で`Guiの大きさ: 大きい`に対応

![Large Gui scale support](https://raw.githubusercontent.com/Yukkuritaku/unicode-fix/refs/heads/1.12.2/readme_assets/unicode_fix_gui_scale_screenshot.png)

</details>

<details><summary>Modの修正関連</summary>

* Modリストで日本語や中国語などのテキストが文字化けるバグの修正(1.7.10、1.8.9 のみ、1.12.2では修正済み)
  * ただ、`MetadataCollection`の読み込みが早くてCoreModでは修正できない非互換Modがあったりします。([非互換Mod](https://github.com/Yukkuritaku/unicode-fix/blob/1.12.2/README_JP.MD#%E9%9D%9E%E4%BA%92%E6%8F%9Bmod)を見てちょ)
* [GTNH Custom Main Menu](https://github.com/GTNewHorizons/Custom-Main-Menu) でスプラッシュテキストが日本語や中国語だと文字化けるバグの修正(1.7.10のみ)

</details>

</details>

### 非互換Mod

[Bow Infinity Fix](https://www.curseforge.com/minecraft/mc-mods/bow-infinity-fix)
: Modリストのテキストで日本語や中国語などで文字化けが発生します。 CoreModの読み込みタイミングよりも`MetadataCollection`の呼び出しが早くて、こちらでは修正できません。

### 導入方法

1. [Minecraft Forge](https://files.minecraftforge.net/net/minecraftforge/forge)を導入する
2. [ここ](https://github.com/Yukkuritaku/unicode-fix/releases/latest)から自分の使いたいバージョンをダウンロードする
3. modsフォルダにこのModのJarを入れる
4. 完了！

---

### ライセンス、Dependencyなど
このプロジェクトのv1.0でリリースしているファイルにはSpongePoweredの[Mixin](https://github.com/SpongePowered/Mixin)を依存関係で使用しており、MITライセンスの条件下で配布しています。
MITライセンスの詳細はこちら: [日本語](https://github.com/Yukkuritaku/unicode-fix/blob/1.12.2/LICENSE_JP.txt), [English](https://github.com/Yukkuritaku/unicode-fix/blob/1.12.2/LICENSE.txt)