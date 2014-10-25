
------------------------------------------------------------------------------------------------------------------------
android:backupAgent=".BrowserBackupAgent"
android:readPermission="com.android.browser.permission.READ_HISTORY_BOOKMARKS"
android:writePermission="com.android.browser.permission.WRITE_HISTORY_BOOKMARKS"

<path-permission android:path="/bookmarks/search_suggest_query"
	android:readPermission="android.permission.GLOBAL_SEARCH" />
------------------------------------------------------------------------------------------------------------------------
	
	

------------------------------------------------------------------------------------------------------------------------
		IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        mPackageInstallationReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                final String packageName = intent.getData()
                        .getSchemeSpecificPart();
                final boolean replacing = intent.getBooleanExtra(
                        Intent.EXTRA_REPLACING, false);
                if (Intent.ACTION_PACKAGE_REMOVED.equals(action) && replacing) {
                    // if it is replacing, refreshPlugins() when adding
                    return;
                }

                if (sGoogleApps.contains(packageName)) {
                    BrowserActivity.this.packageChanged(packageName,
                            Intent.ACTION_PACKAGE_ADDED.equals(action));
                }

                PackageManager pm = BrowserActivity.this.getPackageManager();
                PackageInfo pkgInfo = null;
                try {
                    pkgInfo = pm.getPackageInfo(packageName,
                            PackageManager.GET_PERMISSIONS);
                } catch (PackageManager.NameNotFoundException e) {
                    return;
                }
                ...
           }
         }
------------------------------------------------------------------------------------------------------------------------
	
------------------------------------------------------------------------------------------------------------------------
	 mSecLockIcon = Resources.getSystem().getDrawable(android.R.drawable.ic_secure);
	 
	 /**
	 * Gets a scale factor that determines the distance the view should scroll vertically in response to
	 * {@link MotionEvent#ACTION_SCROLL}.
	 * 
	 * @return The vertical scroll scale factor.
	 */
	protected float getHorizontalScrollFactor() {
		if ( mHorizontalScrollFactor == 0 ) {

			int resId = getResources().getIdentifier( "sephiroth_listPreferredItemWidth", "attr", getContext().getPackageName() );
			
			if( resId != 0 ) {
				TypedValue outValue = new TypedValue();
				boolean success = getContext().getTheme().resolveAttribute( resId, outValue, true );
				
				if( success ) {
					mHorizontalScrollFactor = outValue.getDimension( getContext().getResources().getDisplayMetrics() );
				} else {
					throw new IllegalStateException("Expected theme to define sephiroth_listPreferredItemWidth." );
				}
			} else {
				throw new IllegalStateException("Expected theme to define sephiroth_listPreferredItemWidth." );
			}
		}
		return mHorizontalScrollFactor;
	} 
------------------------------------------------------------------------------------------------------------------------
PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Browser"); 
------------------------------------------------------------------------------------------------------------------------ 