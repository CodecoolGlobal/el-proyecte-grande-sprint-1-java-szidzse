import { Card, Typography } from "@material-tailwind/react";
import EditButton from "../EditButton";
import DeleteButton from "../DeleteButton";

export default function AccommodationTable({ accommodations }) {
	const TABLE_HEAD = ["Accommodation Name", "Location", "Actions"];

	return (
		<Card className="h-full w-full overflow-scroll">
			<table className="w-full min-w-max table-auto text-left">
				<thead>
					<tr>
						{TABLE_HEAD.map((head) => (
							<th key={head} className="border-b border-blue-gray-100 bg-blue-gray-50 p-4">
								<Typography
									variant="small"
									color="blue-gray"
									className="font-normal leading-none opacity-70"
								>
									{head}
								</Typography>
							</th>
						))}
					</tr>
				</thead>
				<tbody>
					{accommodations.map(({ id, name, location }, index) => {
						const isLast = index === accommodations.length - 1;
						const classes = isLast ? "p-4" : "p-4 border-b border-blue-gray-50";

						return (
							<tr key={id}>
								<td className={classes}>
									<Typography variant="small" color="blue-gray" className="font-normal">
										{name}
									</Typography>
								</td>
								<td className={classes}>
									<Typography variant="small" color="blue-gray" className="font-normal">
										{location.city} {location.state ? location.state : ""} {location.country}
									</Typography>
								</td>
								<td className={classes}>
									<EditButton accommodationId={id} />
									<DeleteButton accommodationId={id} />
								</td>
							</tr>
						);
					})}
				</tbody>
			</table>
		</Card>
	);
}
