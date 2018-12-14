/* See the file "LICENSE" for the full license governing this code. */
package com.cognizant.covr;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class VisitorController {

	private final VisitorRepository visitorRepository;

	VisitorController(final VisitorRepository visitorRepository) {
		this.visitorRepository = visitorRepository;
	}

	@PostMapping("/")
	Visitor create(@RequestBody final Visitor newVisitor) {
		return visitorRepository.save(newVisitor);
	}

	@GetMapping("/")
	Iterable<Visitor> list() {
		return visitorRepository.findAll();
	}

	@GetMapping("/counter")
	int incrementCounter(){


		return VisitorCounter.incrementCounterAndGet();

	}
}

