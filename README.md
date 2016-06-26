AndroidFlux
===

[![License](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/ttymsd/AndroidFlux/blob/master/LICENSE.txt)
[![Build Status](https://travis-ci.org/ttymsd/AndroidFlux.svg?branch=master)](https://travis-ci.org/ttymsd/AndroidFlux)
[![Download](https://api.bintray.com/packages/ttymsd/maven/jp.bglb.bonboru%3Aflux-processor/images/download.svg) ](https://bintray.com/ttymsd/maven/jp.bglb.bonboru%3Aflux-processor/_latestVersion)

Flux Framework for Java

Usage
-----

Add dependency to `build.gradle`.

```gradle
repositories {
    maven {
        url  "http://dl.bintray.com/ttymsd/maven" 
    }
}
dependencies {
    compile 'jp.bglb.bonboru:flux-framework:${latestVersion}'
    compile 'jp.bglb.bonboru:flux-framework-kotlin:${latestVersion}'
    compile 'jp.bglb.bonboru:flux-processor:${latestVersion}'
    apt 'jp.bglb.bonboru:flux-processor:${latestVersion}'
}
```

Java Code see [example](https://github.com/ttymsd/AndroidFlux/tree/master/example "example")
Kotlin Code see [kotlin#example](https://github.com/ttymsd/AndroidFlux/tree/master/kotlinexample "kotlinexample")

License
---

```text
Copyright 2016 Tetsuya Masuda

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


