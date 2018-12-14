package com.cognizant.covr;

import com.google.common.testing.EqualsTester;
import org.junit.Test;

public class VisitorTest {

	@Test
	public void equalsAndHashCodeContractIsValid() {
		new EqualsTester()
				.addEqualityGroup(
						new Visitor(0L, "8675309", "Sarah", "+11234567890"),
						new Visitor(0L, "8675309", "Sarah", "+11234567890")
				)
				.addEqualityGroup(new Visitor(1L, "8675309", "Sarah", "+11234567890"))
				.addEqualityGroup(new Visitor(0L, "8675309_", "Sarah", "+11234567890"))
				.addEqualityGroup(new Visitor(0L, "8675309", "Sarah_", "+11234567890"))
				.addEqualityGroup(new Visitor(0L, "8675309", "Sarah", "+11234567890_"))
				.testEquals();
	}
}