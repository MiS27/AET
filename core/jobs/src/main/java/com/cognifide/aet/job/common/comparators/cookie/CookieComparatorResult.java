/**
 * Automated Exploratory Tests
 *
 * Copyright (C) 2013 Cognifide Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cognifide.aet.job.common.comparators.cookie;

import org.openqa.selenium.Cookie;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CookieComparatorResult implements Serializable {

  private static final long serialVersionUID = -3504585587159733351L;

  private final CompareAction compareAction;

  private final HashSet<Cookie> cookies;

  public CookieComparatorResult(CompareAction compareAction, Set<Cookie> cookies) {
    this.compareAction = compareAction;
    this.cookies = new HashSet<>(cookies);
  }

  public CompareAction getCompareAction() {
    return compareAction;
  }

  public Set<Cookie> getCookies() {
    return cookies;
  }

}
