/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.gradle.dependencymanagement


class DependencyManagementHandler {

	private DependencyManagement dependencyManagement

	DependencyManagementHandler(DependencyManagement dependencyManagement) {
		this.dependencyManagement = dependencyManagement
	}

	void imports(Closure closure) {
		closure.setResolveStrategy(Closure.DELEGATE_ONLY)
		closure.delegate = new ImportsHandler()
		closure.call()
	}

	void dependencies(Closure closure) {
		closure.setResolveStrategy(Closure.DELEGATE_ONLY)
		closure.delegate = new DependenciesHandler(dependencyManagement)
		closure.call()
	}

	class ImportsHandler {
		void mavenBom(String coordinates) {
			dependencyManagement.importBom(coordinates)
		}
	}
}
