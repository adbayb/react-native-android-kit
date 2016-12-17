export const getDisplayName = (component) => {
	//a && b: if a = true and b = true then b
	return component && (component.displayName || component.name || null);
};

export const elementOf = (expectedElement) => {
	return (props, propName, componentName) => {
		const prop = props[propName];
		if (prop) {
			const actualElement = prop.type;
			if (actualElement) {
				const actualElementName = getDisplayName(actualElement);
				const expectedElementName = getDisplayName(expectedElement);
				if (expectedElementName) {
					if (actualElementName === expectedElementName) {
						return null;
					}

					return new Error(`Invalid child supplied to \`${componentName}\`, expected an element of type \`${expectedElementName}\`.`);
				}

				return new Error(`Invalid child supplied to \`${componentName}\`, no element specified.`);
			}

			return new Error(`Invalid child supplied to \`${componentName}\`, an error occurred.`);
		}
	};
};
