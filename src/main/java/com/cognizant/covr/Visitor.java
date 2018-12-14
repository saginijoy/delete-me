/* See the file "LICENSE" for the full license governing this code. */
package com.cognizant.covr;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public final class Visitor {

	private final                    String employeeId;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitor_sequence")
	private final                    Long   id;
	private final @NotBlank @NotNull String mobile;
	private final @NotBlank @NotNull String name;

	/**
	 * Private constructor required by JPA
	 */
	private Visitor() {
		this(null, null, null);
	}

	/**
	 * Create a Visitor with a null ID for use in tests.
	 *
	 * @param employeeId
	 * 		Optional employeeId for this visitor.
	 * @param name
	 * 		Required name for this visitor.
	 * @param mobile
	 * 		Required mobile number for this visitor.
	 */
	Visitor(
			final String employeeId,
			final String name,
			final String mobile
	) {
		this(null, employeeId, name, mobile);
	}

	@JsonCreator
	Visitor(
			@JsonProperty("id") final Long id,
			@JsonProperty("employeeId") final String employeeId,
			@JsonProperty("name") final String name,
			@JsonProperty("mobile") final String mobile
	) {
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
		this.mobile = mobile;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Visitor visitor = (Visitor) o;
		return Objects.equals(employeeId, visitor.employeeId) &&
				Objects.equals(id, visitor.id) &&
				mobile.equals(visitor.mobile) &&
				name.equals(visitor.name);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public Long getId() {
		return id;
	}

	public String getMobile() {
		return mobile;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId, id, mobile, name);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Visitor.class.getSimpleName() + "[", "]")
				.add("employeeId='" + employeeId + "'")
				.add("id=" + id)
				.add("mobile='" + mobile + "'")
				.add("name='" + name + "'")
				.toString();
	}
}
