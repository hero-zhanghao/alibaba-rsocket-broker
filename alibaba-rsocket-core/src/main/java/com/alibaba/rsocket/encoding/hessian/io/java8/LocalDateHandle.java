/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.rsocket.encoding.hessian.io.java8;


import com.caucho.hessian.io.HessianHandle;

import java.io.Serializable;
import java.time.LocalDate;

public class LocalDateHandle implements HessianHandle, Serializable {
    private static final long serialVersionUID = 166018689500019951L;

    private int year;
    private int month;
    private int day;

    public LocalDateHandle() {
    }

    public LocalDateHandle(LocalDate o) {
        this.year = o.getYear();
        this.month = o.getMonthValue();
        this.day = o.getDayOfMonth();
    }

    public Object readResolve() {
        return LocalDate.of(year, month, day);
    }
}
