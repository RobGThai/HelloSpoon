echo 'Remove apks'
rm debug.apk
rm test.apk
. setting.properties

if [ "$path_to_apk" ] && [ "$application_apk" ] && [ "$test_apk" ]; then
  echo 'Copy apks to current path'

  cp $path_to_apk/$application_apk debug.apk
  if [ "$?" -ne "0" ]; then
    echo "Could not copy application apk at: $path_to_apk/$application_apk"
    exit 1
  fi

  cp $path_to_apk/$test_apk test.apk
  if [ "$?" -ne "0" ]; then
    echo "Could not copy test apk at: $path_to_apk/$test_apk"
    exit 1
  fi

  ls -l *.apk
else
  echo 'Please set paths in setting.properties'
fi
