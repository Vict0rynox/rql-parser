package org.victorynox.rql.parser.value;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScalarValueTest {

	private static Stream<Arguments> getTypeClassData() {
		return Stream.of(
				Arguments.of(new ScalarValue<>(10), Integer.class),
				Arguments.of(new ScalarValue<>(10.10f), Float.class),
				Arguments.of(new ScalarValue<>(0.0002), Double.class),
				Arguments.of(new ScalarValue<>(true), Boolean.class),
				Arguments.of(new ScalarValue<>(false), Boolean.class),
				Arguments.of(new ScalarValue<>("string"), String.class)
		);
	}

	@ParameterizedTest
	@MethodSource("getTypeClassData")
	void getTypeClass(ScalarValue value, Class expectedClass) {
		assertEquals(expectedClass, value.getTypeClass());
	}

	private static Stream<Arguments> getEqualsTrueData()
	{
		return Stream.of(
				Arguments.of(new ScalarValue<>(10), new ScalarValue<>(10)),
				Arguments.of(new ScalarValue<>(0.02), new ScalarValue<>(0.02)),
				Arguments.of(new ScalarValue<>(432.12f), new ScalarValue<>(432.12f)),
				Arguments.of(new ScalarValue<>("str"), new ScalarValue<>("str")),
				Arguments.of(new ScalarValue<>(true), new ScalarValue<>(true)),
				Arguments.of(new ScalarValue<>(false), new ScalarValue<>(false)),
				Arguments.of(new ScalarValue<>(null), new ScalarValue<>(null)),
				Arguments.of(new ScalarValue<>(10), 10),
				Arguments.of(new ScalarValue<>(0.02), 0.02),
				Arguments.of(new ScalarValue<>(432.12f), 432.12f),
				Arguments.of(new ScalarValue<>("str"), "str"),
				Arguments.of(new ScalarValue<>(true), true),
				Arguments.of(new ScalarValue<>(false), false),
				Arguments.of(new ScalarValue<>(null), null)
		);
	}

	private static Stream<Arguments> getEqualsFalseData()
	{
		return Stream.of(
				Arguments.of(new ScalarValue<>(10), new ScalarValue<>(10.92)),
				Arguments.of(new ScalarValue<>(0.02), new ScalarValue<>(2)),
				Arguments.of(new ScalarValue<>(432.12f), new ScalarValue<>(432.12)),
				Arguments.of(new ScalarValue<>("str"), new ScalarValue<>(10)),
				Arguments.of(new ScalarValue<>(true), new ScalarValue<>(false)),
				Arguments.of(new ScalarValue<>(false), new ScalarValue<>(true)),
				Arguments.of(new ScalarValue<>(null), new ScalarValue<>("null")),
				Arguments.of(new ScalarValue<>(10), 10.01),
				Arguments.of(new ScalarValue<>(0.02), 0.03),
				Arguments.of(new ScalarValue<>(432.12f), 432.12),
				Arguments.of(new ScalarValue<>("str"), 543),
				Arguments.of(new ScalarValue<>(true), false),
				Arguments.of(new ScalarValue<>(false), null),
				Arguments.of(new ScalarValue<>(null), "null")
		);
	}

	@ParameterizedTest
	@MethodSource("getEqualsTrueData")
	void equals_True(ScalarValue value, Object comparable)
	{
		assertTrue(value.equals(comparable));
	}

	@ParameterizedTest
	@MethodSource("getEqualsFalseData")
	void equals_False(ScalarValue value, Object comparable)
	{
		assertFalse(value.equals(comparable));
	}
}