/* See the file "LICENSE" for the full license governing this code. */
package com.cognizant.covr;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
final class CovrController {

	@GetMapping("/")
	Iterable<?> list() {
		return Collections.emptyList();
	}
}
