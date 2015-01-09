<div class="admin-moonlight">
	<div class="tabstrip">
		<ul>
			<li class="k-state-active">Basic</li>
			<li>default_Shows_section</li>
			<li>default_Misc_section</li>
			<li>Shows</li>
		</ul>

		<div>
			<div id='element_#: productsId#_Basic'
				class='demo-section k-content plugmin-container'
				ng-controller='controller_#: productsId#_Basic'>
				<div class="angular-view-wrapper">
					<div style="display: block;" class="shield">
						<div class="shield-ldr"></div>
					</div>
					<div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Products Description Name
								</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="text" kendo-auto-complete="" class=""
										ng-model="Products.productsDescription_productsName"
										k-options="productsDescription_productsName">
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Language</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="text" class="k-textbox "
										ng-model="Products.language">
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Genre</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="text" class="k-textbox " ng-model="Products.genre">
								</div>
							</div>
						</div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Products Status</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="radio" ng-model="Products.productsStatus">
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Director</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="text" class="k-textbox "
										ng-model="Products.director">
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Duration</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input kendo-numeric-text-box="" ng-model="Products.duration"
										k-options="duration">
								</div>
							</div>
						</div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Products Image</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="file" name="files" kendo-upload=""
										k-select="productsImage_changed"
										k-remove="productsImage_removed" k-options="productsImage">
								</div>
							</div>
						</div>
						<div class="plugmin-row"></div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Rating</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input kendo-numeric-text-box="" ng-model="Products.rating"
										k-options="rating">
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Products Description Url</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="text" class="k-textbox "
										ng-model="Products.productsDescription_productsUrl">
								</div>
							</div>
						</div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Custom 6</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<select id="custom6" kendo-multi-select="" class=""
										ng-model="Products.custom6" k-options="custom6"></select>
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Custom 1 Int</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input kendo-numeric-text-box="" ng-model="Products.custom1int"
										k-options="custom1int">
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Custom 2 Int</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input kendo-numeric-text-box="" ng-model="Products.custom2int"
										k-options="custom2int">
								</div>
							</div>
						</div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Products Description Field
								</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value width-x2">
									<textarea style="height: 5em" kendo-editor=""
										ng-model="Products.productsDescription_productsDescriptionField"
										k-options="productsDescription_productsDescriptionField"></textarea>
								</div>
							</div>
						</div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Manufacturers</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<select id="manufacturers" kendo-drop-down-list="" class=""
										ng-model="Products.manufacturers.manufacturersId"
										k-options="manufacturers"></select>
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Venue</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<select id="venue" kendo-drop-down-list="" class=""
										ng-model="Products.venue.venueId" k-options="venue"></select>
								</div>
							</div>
							<div class="plugmin-field">
								<div class="plugmin-field-name">Event Organizer</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<select id="eventOrganizer" kendo-drop-down-list="" class=""
										ng-model="Products.eventOrganizer.eoId"
										k-options="eventOrganizer"></select>
								</div>
							</div>
						</div>
						<button class="k-button" ng-click="onSubmit()">Submit</button>
					</div>
				</div>
			</div>
		</div>

		<div>
			<div id='element_#: productsId#_default_Shows_section'
				class='demo-section k-content plugmin-container'
				ng-controller='controller_#: productsId#_default_Shows_section'>
				<div class="angular-view-wrapper">
					<div style="display: block;" class="shield">
						<div class="shield-ldr"></div>
					</div>
					<div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Products Date Available</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input kendo-date-time-picker=""
										ng-model="Products.productsDateAvailable"
										k-options="productsDateAvailable">
								</div>
							</div>
						</div>
						<button class="k-button" ng-click="onSubmit()">Submit</button>
					</div>
				</div>
			</div>
		</div>

		<div>
			<div id='element_#: productsId#_default_Misc_section'
				class='demo-section k-content plugmin-container'
				ng-controller='controller_#: productsId#_default_Misc_section'>
				<div class="angular-view-wrapper">
					<div style="display: block;" class="shield">
						<div class="shield-ldr"></div>
					</div>
					<div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Venue Address</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="text" class="k-textbox "
										ng-model="Products.venueAddress">
								</div>
							</div>
						</div>
						<div class="plugmin-row">
							<div class="plugmin-field">
								<div class="plugmin-field-name">Venue Address Book Entry
									Telephone</div>
								<div class="plugmin-seperator">:</div>
								<div class="plugmin-field-value">
									<input type="text" class="k-textbox "
										ng-model="Products.venue_addressBook_entryTelephone">
								</div>
							</div>
						</div>
						<button class="k-button" ng-click="onSubmit()">Submit</button>
					</div>
				</div>
			</div>
		</div>



		<div>
			<div class="Products-shows-section"></div>
		</div>

	</div>
</div>