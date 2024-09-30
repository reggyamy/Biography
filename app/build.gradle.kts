plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.kapt")
	id("com.google.devtools.ksp")
	id("org.jetbrains.kotlin.plugin.parcelize")
	id("com.google.dagger.hilt.android")
}

android {
	namespace = "com.reggya.biography"
	compileSdk = 34
	
	defaultConfig {
		applicationId = "com.reggya.biography"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		
		this.buildConfigField("String", "BASE_URL", "${properties["BASE_URL"]}")
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		viewBinding = true
		dataBinding = true
		buildConfig = true
	}
}

dependencies {
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	implementation(libs.kotlin.stdlib)
	
	//dagger hilt
	implementation (libs.hilt.android.v2461)
	kapt (libs.hilt.compiler)
	
	//viewmodel
	implementation(libs.activity.ktx)
	implementation(libs.fragment.ktx)
	
	//retrofit
	implementation(libs.retrofit2.retrofit)
	implementation(libs.retrofit2.converter.gson)
	implementation(libs.okhttp3.logging.interceptor)
	
	//gilde
	implementation(libs.glide)
}

kapt {
	correctErrorTypes = true
}
