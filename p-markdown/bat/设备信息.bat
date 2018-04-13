@echo off

adb shell "su -c 'getprop ro.product.model'" 
adb shell "su -c 'dumpsys battery'" 
adb shell "su -c 'wm size'" 

adb shell "su -c 'wm density'" 
adb shell "su -c 'dumpsys window displays'" 
adb shell "su -c 'settings get secure android_id'" 
adb shell "su -c 'dumpsys iphonesubinfo'" 
adb shell "su -c 'service call iphonesubinfo 1'" 
adb shell "su -c 'getprop ro.build.version.release'" 
adb shell "su -c 'ifconfig | grep Mask'" 
adb shell "su -c 'cat /sys/class/net/wlan0/address'" 
adb shell "su -c 'cat /proc/cpuinfo'" 
adb shell "su -c 'cat /system/build.prop'" 
pause









