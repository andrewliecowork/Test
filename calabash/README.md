# A. 執行測試
## 測試 Android 執行方式

如果使用 calabash-sandbox 請先執行 `calabash-sandbox` 再執行以下指令

```
$ calabash-android resign prebuilt/android/app-debug.apk
$ calabash-android run prebuilt/android/app-debug.apk -p android
```


## 測試 iOS 執行方式

如果使用 calabash-sandbox 請先執行 `calabash-sandbox` 再執行以下指令

```
$ cucumber -p ios
```

## 報告產生方式
測試時，指令後面加上 `--format html --out YOUR_REPORT_NAME.html` 如：

```
$ cucumber -p ios --format html --out reports-ios.html
```

```
$ calabash-android run prebuilt/android/app-debug.apk -p android --format html --out reports-ios.html
```

---

# B. 測試撰寫注意事項
- Android 的函式在使用上要注意，如果有用到 Support Library，有些元件會被改為 v7 的元件。<br/>例如：android.widget.EditText 被改為 android.support.v7.widget.EditText
- 部分功能在 Android 和 iOS 命名有可能只差一個字。
- 盡量使用 iOS 和 Android 同時提供的功能。
- 使用 console 來找出或是測試要使用的元件，執行 console 後的 irb 環境下執行 `start_test_server_in_background` 即可。
    - Android
        ```
        calabash-android console prebuilt/android/app-debug.apk
        irb(main):001:0> start_test_server_in_background
        ```
    - iOS
        ```
        calabash-ios console prebuilt/ios/zDrive.app
        irb(main):001:0> start_test_server_in_background
        ```

---

# C. 相關資源

- Calabash 提供的 Methods： [https://github.com/calabash/calabash/wiki/Identical-methods-in-Calabash](https://github.com/calabash/calabash/wiki/Identical-methods-in-Calabash)
- iOS 和 Android 的 Method 比較表： [https://github.com/calabash/calabash/wiki/Different-methods-in-Calabash](https://github.com/calabash/calabash/wiki/Different-methods-in-Calabash)

## Android 預先定義 step
- Wiki:
    [https://github.com/calabash/calabash-android/blob/master/ruby-gem/lib/calabash-android/canned_steps.md](https://github.com/calabash/calabash-android/blob/master/ruby-gem/lib/calabash-android/canned_steps.md)

- 位置：
    ```
    ~/.calabash/sandbox/Gems/gems/calabash-android-0.8.0/lib/calabash-android/steps
    ```

## iOS 預先定義 step
- Wiki: 
    [https://github.com/calabash/calabash-ios/wiki/02-Predefined-steps](https://github.com/calabash/calabash-ios/wiki/02-Predefined-steps)

- 位置：
    ```
    ~/.calabash/sandbox/Gems/gems/calabash-cucumber-0.19.2/features/step_definitions/calabash_steps.rb
    ```

## Query 語法

- 官網：[https://developer.xamarin.com/guides/testcloud/calabash/calabash-query-syntax/](https://developer.xamarin.com/guides/testcloud/calabash/calabash-query-syntax/)

### 範例

- 找出目前畫面上所有的 View

    ```
    query("*")
    ```

- 找出所有目前畫面上 ID 為 "some_label" 的 View

    ```
    query("* id:'some_label'")
    ```

- 找出某個 UICollectionView 下的第 1 個 Cell 內，文字或 ID 為 "Hello" 的元件

    ```
    query("UICollectionView UICollectionViewCell index:0 * marked:'Hello'")
    ```

- 找出畫面上文字為 "HelloWorld" 的 View

    ```
    query("* {text CONTAINS[c] 'HelloWorld'")
    ```

---

# D. 測試 App 產生方式
## Android
參考官方: [https://developer.xamarin.com/guides/testcloud/calabash/quickstarts/android-studio/](https://developer.xamarin.com/guides/testcloud/calabash/quickstarts/android-studio/)

1. 因為背後是用 HTTP Server 執行這些操作，因此要加上以下權限在 `AndroidManifest.xml` 內。
    ```
    <uses-permission android:name="android.permission.INTERNET" />
    ```
2. Build 出 apk 檔後執行以下指令用 Debug Key 重新 Sign 過
    ```
    $ calabash-android resign <PATH TO YOUR APK>
    ```

## iOS
參考官方: [https://developer.xamarin.com/guides/testcloud/calabash/quickstarts/xcode/](https://developer.xamarin.com/guides/testcloud/calabash/quickstarts/xcode/)

1. 執行 `calabash-ios download` 下載 calabash framework
2. 在 XCode 的「Build Settings」找到「Other Link Flags」加上
    ```
    -ObjC -force_load "$(SOURCE_ROOT)/calabash.framework/calabash" -framework CFNetwork
    ```
3. 將 XCode build 好的 `.app` 或是 `.ipa` 資料夾複製到指定位置，目前我在 `config/cucumber.yml` 內是設定在 `prebuilt/ios/zDrive.app`
    - `.app` 位置會在 `~/Library/Developer/Xcode/DerivedData/{app name}/Build/Products/` 底下。


---

# 附錄. 環境安裝
請用以下兩種擇一即可。

## 用 Calabash Sandbox 安裝
參考官方: [https://developer.xamarin.com/guides/testcloud/calabash/quickstarts/android-studio/installing-gems/](https://developer.xamarin.com/guides/testcloud/calabash/quickstarts/android-studio/installing-gems/)

安裝 calabash-sandbox

```
$ curl -sSL https://raw.githubusercontent.com/calabash/install/master/install-osx.sh | bash
```

## 自行安裝

1. 安裝 RVM
    參考: [https://rvm.io/](https://rvm.io/)

    ```
    $ gpg --keyserver hkp://keys.gnupg.net --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3
    $ \curl -sSL https://get.rvm.io | bash -s stable
    ```

2. 安裝 Ruby

    先更新一下 brew ，避免遇到不可預期的問題
    ```
    brew update
    ```

    ```
    $ rvm install ruby-2.2.5
    $ rvm --default use 2.2.5
    ```

3. 安裝 Calabash 的 gem

    ```
    $ gem install calabash-cucumber --no-ri --no-rdoc
    $ gem install calabash-android --no-ri --no-rdoc
    ```

# 附錄. 疑難排解

1. 遇到以下錯誤訊息:

	```
android.util.AndroidException: INSTRUMENTATION_FAILED:
com.zyxel.zdrive.test/sh.calaba.instrumentationbackend.CalabashInstrumentationTestRunner
          at com.android.commands.am.Am.runInstrument(Am.java:1093)
          at com.android.commands.am.Am.onRun(Am.java:371)
          at com.android.internal.os.BaseCommand.run(BaseCommand.java:47)
          at com.android.commands.am.Am.main(Am.java:100)
          at com.android.internal.os.RuntimeInit.nativeFinishInit(Native Method)
          at com.android.internal.os.RuntimeInit.main(RuntimeInit.java:251)
 場景: 測試以帳號 marslin@ecoworkinc.com，密碼 zyxeltest 進行登入
 App did not start (RuntimeError)
 ./features/android/support/app_life_cycle_hooks.rb:21:in `Before'
	```

	解法:

	```
	1. Uninstall the app from the device
	2. Clear the test_servers folder
	3. Installed app via adb install app.apk
	4. From terminal:
		a. bundle exec calabash-android console app.apk
		b. reinstall_apps (no errors occur)
		c. start_test_server_in_background
	```

2. iOS 才會遇到，執行 cucumber 時遇到

    ```
    undefined method `strip' for nil:NilClass (NoMethodError)
    ```
    
    先執行 `xcode-select -p` 確認結果應該要如下:
    
    ```
    /Applications/Xcode.app/Contents/Developer
    ```
    
    若不是則要執行 `xcode-select --install` 若出現
    
    ```
    xcode-select: error: command line tools are already installed, use "Software Update" to install update
    ```

    則執行 `sudo xcode-select -r`
    
    參考：
    
    - [https://github.com/Homebrew/brew/issues/972](https://github.com/Homebrew/brew/issues/972)
    - [https://developer.xamarin.com/guides/testcloud/calabash/configuring/osx/install-xcode-command-line-tools/](https://developer.xamarin.com/guides/testcloud/calabash/configuring/osx/install-xcode-command-line-tools/)

3. iOS 憑證在 Keychain 確實有安裝，但是要產生 Archive 時出現 `provisioning profile doesn't include signing certificate`。

    解法: 檢查是否在 Keychain 內有重複名稱的憑證，因為 XCode 會以最先找到的憑證為準。
    參考: [http://stackoverflow.com/questions/39568005/xcode-8-shows-error-that-provisioning-profile-doesnt-include-signing-certificat](http://stackoverflow.com/questions/39568005/xcode-8-shows-error-that-provisioning-profile-doesnt-include-signing-certificat)