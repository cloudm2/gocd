#!/bin/sh
#*************************GO-LICENSE-START********************************
# Copyright 2014 ThoughtWorks, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or a-qgreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#*************************GO-LICENSE-END**********************************

if [ $0 = './b' ]; then
    project_root=`pwd`
else
    project_root=`echo $0 | sed -e 's#\/b$##g'`
fi
export TF_CLC_HOME=$project_root/tfs-tool
unset GEM_HOME
unset GEM_PATH
$(dirname $0)/tools/jruby/bin/jruby -J-Xmx2048m -S buildr $*
