package com.globant.aimate.storage;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

	@Override
	public final JsonElement serialize(final T object, final Type interfaceType,
			final JsonSerializationContext context) {
		final JsonObject member = new JsonObject();
		member.addProperty("type", object.getClass().getName());
		Gson gson = new Gson();
		member.add("data", gson.toJsonTree(object, interfaceType));
		return member;
	}

	@Override
	public final T deserialize(final JsonElement elem, final Type interfaceType,
			final JsonDeserializationContext context) {
		final JsonObject member = (JsonObject) elem;
		final JsonElement typeString = get(member, "type");
		final JsonElement data = get(member, "data");
		final Type actualType = typeForName(typeString);
		return context.deserialize(data, actualType);
	}

	private Type typeForName(final JsonElement typeElem) {
		try {
			return Class.forName(typeElem.getAsString());
		} catch (ClassNotFoundException e) {
			throw new JsonParseException(e);
		}
	}

	private JsonElement get(final JsonObject wrapper, final String memberName) {
		final JsonElement elem = wrapper.get(memberName);

		if (elem == null) {
			throw new JsonParseException("no '" + memberName + "' member found in json file.");
		}
		return elem;
	}

}