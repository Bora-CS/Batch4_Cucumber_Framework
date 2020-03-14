package cucumberDataTransformers;

import java.util.List;
import java.util.Locale;

import dataObjects.LogInData;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableRowTransformer;

public class Configurer implements TypeRegistryConfigurer {

	@Override
	public void configureTypeRegistry(TypeRegistry registry) {
		registry.defineDataTableType(new DataTableType(LogInData.class, new TableRowTransformer<LogInData>() {
			@Override
			public LogInData transform(List<String> entry) throws Throwable {
				return new LogInData(entry.get(0), entry.get(1));
			}
		}));
	}

	public Locale locale() {
		return Locale.ENGLISH;
	}

}
