echo 'Remove apks'
rm debug.apk
rm test.apk
echo 'Copy apks to current path'
path_to_apk=../app/build/outputs/apk
cp $path_to_apk/app-debug.apk debug.apk
cp $path_to_apk/app-debug-test-unaligned.apk test.apk
ls -l *.apk
